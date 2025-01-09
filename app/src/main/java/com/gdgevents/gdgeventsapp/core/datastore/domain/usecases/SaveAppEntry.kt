package com.gdgevents.gdgeventsapp.core.datastore.domain.usecases

import com.gdgevents.gdgeventsapp.core.datastore.domain.UserPreferencesRepo

class SaveAppEntry(private val userPreferencesRepo: UserPreferencesRepo) {

    suspend operator fun invoke(){
        userPreferencesRepo.saveAppEntry(true)
    }
}