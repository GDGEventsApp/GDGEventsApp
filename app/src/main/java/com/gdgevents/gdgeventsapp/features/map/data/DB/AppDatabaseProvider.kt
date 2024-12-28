package com.gdgevents.gdgeventsapp.features.map.data.DB

import android.content.Context
import androidx.room.Room

class AppDatabaseProvider private constructor(context: Context) {

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
            }
            return instance!!
        }
    }
}