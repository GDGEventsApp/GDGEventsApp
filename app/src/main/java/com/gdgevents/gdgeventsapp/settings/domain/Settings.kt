package com.gdgevents.gdgeventsapp.settings.domain

// external object for UserPreferences to decouple from datastore
data class Settings(
    val isDarkTheme: Boolean,
    // add remaining
)
