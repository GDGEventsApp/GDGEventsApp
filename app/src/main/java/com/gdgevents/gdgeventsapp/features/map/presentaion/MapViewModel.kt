package com.gdgevents.gdgeventsapp.features.map.presentaion

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgevents.gdgeventsapp.features.map.data.db.LocationDao
import com.gdgevents.gdgeventsapp.features.map.data.db.LocationEntity
import com.gdgevents.gdgeventsapp.features.map.data.MapState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val locationDao: LocationDao
) : ViewModel() {

    private val _state = MutableStateFlow(MapState())
    val state: StateFlow<MapState> = _state

    private val _dynamicText = MutableStateFlow("Choose your location to start to find events around you")
    val dynamicText: StateFlow<String> = _dynamicText

    private val _userLocation = MutableStateFlow<LatLng?>(null)


    private val _isDeleting = MutableStateFlow(false)


    fun fetchUserLocation(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("MapViewModel", "Location permission not granted.")
            return
        }

        try {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        val userLatLng = LatLng(it.latitude, it.longitude)


                        _userLocation.value = userLatLng


                        _state.value = _state.value.copy(
                            marker = userLatLng
                        )

                        viewModelScope.launch {

                            val governorate = getGovernorateFromLocation(context, userLatLng)
                            locationDao.insertLocation(
                                LocationEntity(
                                    latitude = it.latitude,
                                    longitude = it.longitude,
                                    governorate = governorate
                                )
                            )
                        }
                    } ?: Log.e("MapViewModel", "Location is null.")
                }
                .addOnFailureListener { exception ->
                    Log.e("MapViewModel", "Failed to fetch location: ${exception.message}")
                }
        } catch (e: SecurityException) {
            Log.e("MapViewModel", "SecurityException: ${e.message}")
        }
    }


    fun addMarker(latLng: LatLng, context: Context) {
        viewModelScope.launch {

            reverseGeocodeAsync(context, latLng) { address ->

                val city =address ?: "Unknown Location"


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
    fun saveLocation(location: LatLng, governorate: String) {
        viewModelScope.launch {
            val locationEntity = LocationEntity(
                latitude = location.latitude,
                longitude = location.longitude,
                governorate = governorate
            )

            val existingLocation = locationDao.getLocation()

            if (existingLocation == null) {

                locationDao.insertLocation(locationEntity)
            } else {

                locationDao.updateLocation(locationEntity)
            }


            _dynamicText.value = governorate
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

                _dynamicText.value = it.governorate
            }
        }
    }

    suspend fun getGovernorateFromLocation(context: Context, latLng: LatLng): String {
        return withContext(Dispatchers.IO) {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (addressList.isNullOrEmpty()) {
                "Unknown Governorate"
            } else {
                val address = addressList[0]
                address.adminArea ?: "Unknown Governorate"
            }
        }
    }
}



