package com.gdgevents.gdgeventsapp

import ahmed.praicticing.compose_practice.permissionManager
import android.app.Application

class MyApplication: Application() {
    lateinit var permissions: permissionManager

    override fun onCreate() {
        super.onCreate()

        permissions = permissionManager(this)
    }
}