package com.gdgevents.gdgeventsapp.core.remote.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // provide retrofit ,etc.
}
