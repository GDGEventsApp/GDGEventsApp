package com.gdgevents.gdgeventsapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

object PermissionManager {
    const val LOCATION_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION

    fun hasLocationPermission(context: Context): Boolean = ContextCompat.checkSelfPermission(
        context,
        LOCATION_PERMISSION
    ) == PackageManager.PERMISSION_GRANTED

    fun requestLocationPermission(
        launcher: ActivityResultLauncher<String>,
    ) {
        launcher.launch(LOCATION_PERMISSION)
    }
}