package com.gdgevents.gdgeventsapp

import ahmed.praicticing.compose_practice.permissionManager
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gdgevents.gdgeventsapp.features.map.presentaion.MapScreen
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("StaticFieldLeak")
private lateinit var PermissionManager: permissionManager

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PermissionManager = (application as GdgApp).permissions
        setContent {

            MapScreen()
        }
    }



}


