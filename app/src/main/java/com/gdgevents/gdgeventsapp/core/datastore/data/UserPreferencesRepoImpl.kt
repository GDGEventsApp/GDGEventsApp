package com.gdgevents.gdgeventsapp.core.datastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.gdgevents.gdgeventsapp.core.datastore.domain.UserPreferencesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

private object PreferencesKey {
    val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
}
class UserPreferencesRepoImpl @Inject constructor(private val context:Context) : UserPreferencesRepo {

    private val dataStore = context.dataStore


    override suspend fun saveAppEntry(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }


}