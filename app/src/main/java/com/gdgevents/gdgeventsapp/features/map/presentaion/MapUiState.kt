package com.gdgevents.gdgeventsapp.features.map.presentaion

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties

val cairo = LatLng(30.0444, 31.2357)

data class MapUiState(
    val marker: LatLng? = cairo,
    val userLocation: LatLng = cairo,
    val locationText: String? = null
)