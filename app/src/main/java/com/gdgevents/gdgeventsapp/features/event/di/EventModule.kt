package com.gdgevents.gdgeventsapp.features.event.di

import com.gdgevents.gdgeventsapp.features.event.data.EventRepoImpl
import com.gdgevents.gdgeventsapp.features.event.domain.EventRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EventModule {

    @Binds
    abstract fun bindsEventRepo(
        eventRepoImpl: EventRepoImpl
    ): EventRepo

}