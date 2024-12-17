package com.gdgevents.gdgeventsapp.features.settings.di

import com.gdgevents.gdgeventsapp.features.settings.data.SettingsRepoImpl
import com.gdgevents.gdgeventsapp.features.settings.domain.SettingsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindsSettingsRepo(
        settingsRepoImpl: SettingsRepoImpl
    ): SettingsRepo

}