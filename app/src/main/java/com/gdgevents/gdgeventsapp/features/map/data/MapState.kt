package com.gdgevents.gdgeventsapp.features.map.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val isfalloutMap: Boolean = false,
    val marker: LatLng? = null,
    val userLocation: LatLng? = null
)
