package com.gdgevents.gdgeventsapp.core.work.di

import com.gdgevents.gdgeventsapp.core.work.data.SyncManagerImpl
import com.gdgevents.gdgeventsapp.core.work.domain.SyncManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkModule {

    @Binds
    abstract fun bindsSyncManager(
        syncManagerImpl: SyncManagerImpl
    ): SyncManager

}