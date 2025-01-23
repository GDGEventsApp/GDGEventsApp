package com.gdgevents.gdgeventsapp.core.datastore.data

import androidx.datastore.core.DataStore
import com.gdgevents.gdgeventsapp.core.datastore.domain.UserPreferencesRepo
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreferencesRepoImpl @Inject constructor(private val localDataSource: DataStore<UserPreferences>) :
    UserPreferencesRepo {
    // add implementation
    override suspend fun setLocation(lat: Double, long: Double, region: String) {
        val location =
            Location.newBuilder().setLatitude(lat).setLongitude(long).setRegion(region).build()
        localDataSource.updateData { it.copy { this.location = location } }
    }

    override suspend fun getLocation(): Location = localDataSource.data.first().location

}