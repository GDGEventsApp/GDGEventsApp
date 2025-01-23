package com.gdgevents.gdgeventsapp.core.datastore.domain

import com.gdgevents.gdgeventsapp.core.datastore.data.Location

interface UserPreferencesRepo {
    suspend fun setLocation(lat: Double, long: Double, region: String)
    suspend fun getLocation(): Location
    // add abstraction
}