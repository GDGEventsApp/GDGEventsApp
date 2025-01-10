package com.gdgevents.gdgeventsapp.features.onBoarding.data.manager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.manager.LocalUserManager
import com.gdgevents.gdgeventsapp.features.onBoarding.util.Constants
import com.gdgevents.gdgeventsapp.features.onBoarding.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    private val Context.dataStore by preferencesDataStore(USER_SETTINGS)
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
    private object PreferencesKeys {
        val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    }
}