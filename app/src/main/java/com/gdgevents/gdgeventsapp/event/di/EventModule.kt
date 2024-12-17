package com.gdgevents.gdgeventsapp.event.di

import com.gdgevents.gdgeventsapp.event.data.EventRepoImpl
import com.gdgevents.gdgeventsapp.event.domain.EventRepo
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