package com.gdgevents.gdgeventsapp

import  com.gdgevents.gdgeventsapp.MyApplication
import ahmed.praicticing.compose_practice.permissionManager
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PermissionViewModel(val permissions: permissionManager) : ViewModel() {

    val uiState = permissions.state

    fun onPermissionChange(requestedPermissions: Map<String, Boolean>) {
        permissions.onPermissionChange(requestedPermissions)
    }

    fun createSettingsIntent(): Intent {
        return permissions.createSettingsIntent()
    }
}


class PermissionViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val app =
            extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication
        return PermissionViewModel(app.permissions) as T
    }
}