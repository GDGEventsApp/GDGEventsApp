package com.gdgevents.gdgeventsapp.settings.di

import com.gdgevents.gdgeventsapp.settings.data.SettingsRepoImpl
import com.gdgevents.gdgeventsapp.settings.domain.SettingsRepo
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