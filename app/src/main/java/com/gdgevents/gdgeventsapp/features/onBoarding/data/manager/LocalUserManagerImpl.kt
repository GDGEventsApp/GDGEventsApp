package com.gdgevents.gdgeventsapp.features.onBoarding.data.manager

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.manager.LocalUserManager
import com.gdgevents.gdgeventsapp.features.onBoarding.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
): LocalUserManager {
    override suspend fun saveAppEntry() {
        try {
            dataStore.edit { settings ->
                settings[PreferencesKeys.APP_ENTRY] = true
            }
        } catch (e: Exception) {
            Log.e("LocalUserManagerImpl", "Failed to save APP_ENTRY", e)
        }
    }
    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            val value = preferences[PreferencesKeys.APP_ENTRY] ?: false
            value
        }
    }
    private object PreferencesKeys {
        val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    }
}