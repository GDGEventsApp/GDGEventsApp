package com.gdgevents.gdgeventsapp.features.map.data.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LocationDao {

    @Insert
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM user_location LIMIT 1")
    suspend fun getLocation(): LocationEntity?

    @Update
    suspend fun updateLocation(location: LocationEntity)

    @Query("DELETE FROM user_location")
    suspend fun deleteAllLocations()
}