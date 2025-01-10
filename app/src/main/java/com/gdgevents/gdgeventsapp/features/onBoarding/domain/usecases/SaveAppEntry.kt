package com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases

import com.gdgevents.gdgeventsapp.features.onBoarding.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
         localUserManager.saveAppEntry()
    }

}