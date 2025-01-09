package com.gdgevents.gdgeventsapp.core.datastore.di

import android.app.Application
import com.gdgevents.gdgeventsapp.core.datastore.data.UserPreferencesRepoImpl
import com.gdgevents.gdgeventsapp.core.datastore.domain.UserPreferencesRepo
import com.gdgevents.gdgeventsapp.core.datastore.domain.usecases.AppEntryUseCases
import com.gdgevents.gdgeventsapp.core.datastore.domain.usecases.ReadAppEntry
import com.gdgevents.gdgeventsapp.core.datastore.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    // provide DataStore
    @Provides
    @Singleton
    fun provideUserPreferencesRepo(application: Application) : UserPreferencesRepo{
        return UserPreferencesRepoImpl(context = application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(userPreferencesRepo: UserPreferencesRepo): AppEntryUseCases {
        return AppEntryUseCases(readAppEntry = ReadAppEntry(userPreferencesRepo),
            saveAppEntry = SaveAppEntry(userPreferencesRepo))
    }
}
