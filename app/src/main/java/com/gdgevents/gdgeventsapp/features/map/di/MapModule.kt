package com.gdgevents.gdgeventsapp.features.map.di

import com.gdgevents.gdgeventsapp.features.map.data.MapRepoImpl
import com.gdgevents.gdgeventsapp.features.map.domain.MapRepo
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