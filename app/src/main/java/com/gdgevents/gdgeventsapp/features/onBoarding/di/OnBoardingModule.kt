package com.gdgevents.gdgeventsapp.features.onBoarding.di

import android.app.Application
import com.gdgevents.gdgeventsapp.features.onBoarding.data.manager.LocalUserManagerImpl
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.manager.LocalUserManager
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases.AppEntryUseCases
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases.ReadAppEntry
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OnBoardingModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager {
        return LocalUserManagerImpl(application)
    }
    @Provides
    @Singleton
    fun provideOnBoardingUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }
}
