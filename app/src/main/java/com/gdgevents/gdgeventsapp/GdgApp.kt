package com.gdgevents.gdgeventsapp

import ahmed.praicticing.compose_practice.permissionManager
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GdgApp: Application() {
    lateinit var permissions: permissionManager

    override fun onCreate() {
        super.onCreate()

        permissions = permissionManager(this)
    }
}