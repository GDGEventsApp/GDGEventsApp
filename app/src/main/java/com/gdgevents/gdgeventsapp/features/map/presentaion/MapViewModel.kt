package com.gdgevents.gdgeventsapp.features.map.presentaion

import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgevents.gdgeventsapp.core.datastore.domain.UserPreferencesRepo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MapViewModel"

@HiltViewModel
class MapViewModel @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val userPrefRepo: UserPreferencesRepo
) : ViewModel() {
    private val _state = MutableStateFlow(MapUiState())
    val state: StateFlow<MapUiState> = _state

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    init {
        getRegionFromLocation(_state.value.marker!!)
        fetchUserLocation()
    }

    fun fetchUserLocation() {
        viewModelScope.launch {
            try {
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location ->
                        location?.let {
                            val userLatLng = LatLng(it.latitude, it.longitude)
                            addMarker(userLatLng)
                        }
                    }.addOnFailureListener { exception ->
                        Log.e(TAG, "exception")
                        Log.e(TAG, "Failed to fetch location: ${exception.message}")
                    }
            } catch (e: SecurityException) {
                Log.e("MapViewModel", "SecurityException: ${e.message}")
            }
        }
    }

    fun addMarker(latLng: LatLng) {
        viewModelScope.launch {
            getRegionFromLocation(latLng = latLng)
            _state.update { it.copy(marker = latLng) }
        }
    }

    // Method to save or update the location in the database
    fun saveLocation() {
        viewModelScope.launch {
            val location = _state.value.marker
            location?.let {
                userPrefRepo.setLocation(
                    lat = it.latitude,
                    long = it.longitude,
                    region = _state.value.regionName
                )
            }
        }
    }

    fun searchLocation(query: String, onResult: (LatLng?) -> Unit) {
        viewModelScope.launch {
            try {
                val addressList = geocoder.getFromLocationName(query, 1)
                if (!addressList.isNullOrEmpty()) {
                    val address = addressList[0]
                    val latLng = LatLng(address.latitude, address.longitude)
                    onResult(latLng)
                } else {
                    onResult(null) // No location found
                }
            } catch (e: Exception) {
                Log.e("MapViewModel", "Error searching location: ${e.message}")
                onResult(null)
            }
        }
    }

    private fun getRegionFromLocation(
        latLng: LatLng,
    ) {
        viewModelScope.launch {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    1,
                    object : Geocoder.GeocodeListener {
                        override fun onGeocode(addresses: MutableList<Address>) {
                            if (addresses.isNotEmpty()) {
                                val address = addresses[0]

                                val locality = address.adminArea ?: "Unknown Location"
                                _state.update { it.copy(regionName = locality) }
                            } else _state.update { it.copy(regionName = "Unknown Location") }
                        }

                        override fun onError(errorMessage: String?) {
                            _message.tryEmit(errorMessage.toString())
                        }
                    }
                )
            } else {
                try {
                    val addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    if (!addressList.isNullOrEmpty()) {
                        val address = addressList[0]
                        val locality = address.adminArea ?: "Unknown Location"
                        _state.update { it.copy(regionName = locality) }
                    } else {
                        _state.update { it.copy(regionName = "Unknown Location") }
                    }
                } catch (e: Exception) {
                    _message.tryEmit(e.message.toString())
                }
            }
        }
    }
}