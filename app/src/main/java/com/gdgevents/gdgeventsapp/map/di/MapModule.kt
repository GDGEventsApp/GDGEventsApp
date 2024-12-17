package com.gdgevents.gdgeventsapp.map.di

import com.gdgevents.gdgeventsapp.map.data.MapRepoImpl
import com.gdgevents.gdgeventsapp.map.domain.MapRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapModule {

    @Binds
    abstract fun bindsMapRepo(
        mapRepoImpl: MapRepoImpl
    ): MapRepo

}