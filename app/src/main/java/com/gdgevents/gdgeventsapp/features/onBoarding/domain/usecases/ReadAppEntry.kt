package com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases

import com.gdgevents.gdgeventsapp.features.onBoarding.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
    ) {
    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}