package com.gdgevents.gdgeventsapp

import ahmed.praicticing.compose_practice.permissionManager
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.tasks.await

class LocationRepo(context: Context) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val permissionManager = permissionManager(context)

    @SuppressLint("MissingPermission", "SuspiciousIndentation")
    suspend fun fetchLocation(): LatLng? {
        var selectedLocation: LatLng? = null
        if (permissionManager.hasAllPermissions) {
            val granted =
                permissionManager.hasAccess(listOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION))
            if (granted) {
                try {
                    val location = fusedLocationClient.getCurrentLocation(
                        com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                        null
                    ).await() // Use Kotlin coroutines to handle asynchronous operations
                    location?.let {
                        val latLng = LatLng(it.latitude, it.longitude)
                        selectedLocation = latLng
                        Log.d("Location", "Location fetched: $latLng")
                    } ?: run {
                        Log.d("Location", "Location is null.")
                    }
                } catch (e: Exception) {
                    Log.e("Location", "Error fetching location: ${e.message}")
                }
            } else {
                permissionManager.checkPermissions()
            }
        }
        return selectedLocation
    }
}