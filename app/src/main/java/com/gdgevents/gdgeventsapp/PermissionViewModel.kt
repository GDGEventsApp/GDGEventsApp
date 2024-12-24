package com.gdgevents.gdgeventsapp

import ahmed.praicticing.compose_practice.permissionManager

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras


class PermissionViewModel(val permissions: permissionManager) : ViewModel() {
    val uiState = permissions.state

    fun onPermissionChange(requestedPermissions: Map<String, Boolean>) {
        permissions.onPermissionChange(requestedPermissions)
    }

    fun createSettingsIntent(): Intent {
        return permissions.createSettingsIntent()
    }
    fun getLocation(){

    }
}


class PermissionViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val app =
            extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GdgApp
        return PermissionViewModel(app.permissions) as T
    }
}