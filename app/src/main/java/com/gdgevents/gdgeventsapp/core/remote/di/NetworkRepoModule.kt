package com.gdgevents.gdgeventsapp.core.remote.di

import com.gdgevents.gdgeventsapp.core.remote.data.NetworkRepoImpl
import com.gdgevents.gdgeventsapp.core.remote.domain.NetworkRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepoModule {

    @Binds
    abstract fun bindsNetworkRepo(
        networkRepoImpl: NetworkRepoImpl
    ): NetworkRepo

}