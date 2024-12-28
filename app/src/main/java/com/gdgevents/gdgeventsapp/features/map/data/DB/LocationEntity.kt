package com.gdgevents.gdgeventsapp.features.map.data.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_location")
data class LocationEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val latitude: Double,
    val longitude: Double,
    val governorate: String
    )