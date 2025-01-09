package com.gdgevents.gdgeventsapp.core.datastore.domain

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepo {
    // add abstraction
    suspend fun saveAppEntry(completed: Boolean)

    fun readAppEntry():Flow<Boolean>
}