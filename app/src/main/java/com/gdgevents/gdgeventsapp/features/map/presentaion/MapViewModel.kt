package com.gdgevents.gdgeventsapp.features.map.presentaion

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.features.map.data.MapState
import com.gdgevents.gdgeventsapp.features.map.data.db.LocationDao
import com.gdgevents.gdgeventsapp.features.map.data.db.LocationEntity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

private const val TAG = "MapViewModel"

@HiltViewModel
class MapViewModel @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val locationDao: LocationDao
) : ViewModel() {
    private val _state = MutableStateFlow(MapState())
    val state: StateFlow<MapState> = _state

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message

    private val _userLocation = MutableStateFlow<LatLng?>(null)

    private val _isDeleting = MutableStateFlow(false)

    fun fetchUserLocation(context: Context) {
        Log.i(TAG, "fetchUserLocation")
        try {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        Log.i(TAG, "location is $it")
                        val userLatLng = LatLng(it.latitude, it.longitude)
                        _userLocation.value = userLatLng
                        viewModelScope.launch {
                            _state.emit(
                                _state.value.copy(
                                    marker = userLatLng
                                )
                            )
                            getGovernorateFromLocation(context, userLatLng)
//                            locationDao.insertLocation(
//                                LocationEntity(
//                                    latitude = it.latitude,
//                                    longitude = it.longitude,
//                                    governorate = governorate
//                                )
//                            )
                        }
                    } ?: CoroutineScope(Dispatchers.Main).launch {
                        _message.emit(context.getString(R.string.error_location_null))
                    }
                }.addOnFailureListener { exception ->
                    Log.e(TAG, "exception")
                    Log.e("MapViewModel", "Failed to fetch location: ${exception.message}")
                }
        } catch (e: SecurityException) {
            Log.e("MapViewModel", "SecurityException: ${e.message}")
        }
    }

    fun addMarker(latLng: LatLng, context: Context) {
        viewModelScope.launch {
            reverseGeocodeAsync(context, latLng) { address ->
                val city = address ?: "Unknown Location"

                launch {
                    locationDao.insertLocation(
                        LocationEntity(
                            latitude = latLng.latitude,
                            longitude = latLng.longitude,
                            governorate = city
                        )
                    )
                }
            }
        }
        _state.value = _state.value.copy(
            marker = latLng
        )
    }

    // Method to save or update the location in the database
    fun saveLocation(governorate: String = _state.value.locationText ?: "") {
        viewModelScope.launch {
            _userLocation.value?.let {
                val locationEntity = LocationEntity(
                    latitude = it.latitude,
                    longitude = it.longitude,
                    governorate = governorate
                )

                val existingLocation = locationDao.getLocation()

                if (existingLocation == null) locationDao.insertLocation(locationEntity)
                else locationDao.updateLocation(locationEntity)
            }
        }
    }


    fun deleteLocation() {
        viewModelScope.launch {
            _isDeleting.value = true
            locationDao.deleteAllLocations()
            _isDeleting.value = false
        }
    }

    fun searchLocation(query: String, context: Context, onResult: (LatLng?) -> Unit) {
        viewModelScope.launch {
            val geocoder = Geocoder(context, Locale.getDefault())
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

    fun loadSavedLocation() {
        viewModelScope.launch {
            val location = locationDao.getLocation()
            location?.let {
                _state.value = _state.value.copy(marker = LatLng(it.latitude, it.longitude))
//                _dynamicText.value = it.governorate
            }
        }
    }

    private suspend fun getGovernorateFromLocation(context: Context, latLng: LatLng): String {
        return withContext(Dispatchers.IO) {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (addressList.isNullOrEmpty()) {
                "Unknown Governorate"
            } else {
                val address = addressList[0]
                _state.emit(_state.value.copy(locationText = "${address.adminArea}, ${address.countryName}"))
                address.adminArea ?: "Unknown Governorate"
            }
        }
    }
}