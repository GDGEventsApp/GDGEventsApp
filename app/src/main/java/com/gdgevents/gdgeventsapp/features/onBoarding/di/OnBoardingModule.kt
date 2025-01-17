package com.gdgevents.gdgeventsapp.features.onBoarding.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.gdgevents.gdgeventsapp.features.onBoarding.data.manager.LocalUserManagerImpl
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.manager.LocalUserManager
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases.AppEntryUseCases
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases.ReadAppEntry
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases.SaveAppEntry
import com.gdgevents.gdgeventsapp.features.onBoarding.util.DataStoreProvider.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OnBoardingModule {
    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> {
        return context.dataStore
    }
    @Provides
    @Singleton
    fun provideLocalUserManager(
        dataStore: DataStore<Preferences>,
    ): LocalUserManager {
        return LocalUserManagerImpl(dataStore)
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

