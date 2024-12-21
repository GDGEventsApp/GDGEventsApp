package com.gdgevents.gdgeventsapp.core.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gdgevents.gdgeventsapp.core.room.domain.EventEntity
import com.gdgevents.gdgeventsapp.core.room.domain.UserEntity

@Database(
    entities = [EventEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GdgDatabase : RoomDatabase() {
    abstract fun getEventDao(): EventDao
    abstract fun getUserDao(): UserDao
}