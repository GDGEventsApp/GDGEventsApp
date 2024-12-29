package com.gdgevents.gdgeventsapp.features.map.data.db


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [LocationEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}