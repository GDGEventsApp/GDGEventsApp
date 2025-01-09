package com.gdgevents.gdgeventsapp.core.datastore.domain.usecases

import com.gdgevents.gdgeventsapp.core.datastore.domain.UserPreferencesRepo
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val userPreferencesRepo: UserPreferencesRepo) {

     operator fun invoke():Flow<Boolean>{
        return userPreferencesRepo.readAppEntry()
    }
}