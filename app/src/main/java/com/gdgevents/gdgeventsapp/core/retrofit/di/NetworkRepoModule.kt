package com.gdgevents.gdgeventsapp.core.retrofit.di

import com.gdgevents.gdgeventsapp.core.retrofit.data.NetworkRepoImpl
import com.gdgevents.gdgeventsapp.core.retrofit.domain.NetworkRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkRepoModule {

    @Binds
    abstract fun bindsMapRepo(
        networkRepoImpl: NetworkRepoImpl
    ): NetworkRepo

}