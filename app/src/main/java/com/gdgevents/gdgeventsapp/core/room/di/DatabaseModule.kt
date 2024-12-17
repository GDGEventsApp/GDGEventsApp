package com.gdgevents.gdgeventsapp.core.room.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    // provide Room database
    // provide Dao
}