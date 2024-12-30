package com.gdgevents.gdgeventsapp.features.map.di

import android.app.Activity
import com.gdgevents.gdgeventsapp.MainActivity
import com.gdgevents.gdgeventsapp.features.map.data.MapRepoImpl
import com.gdgevents.gdgeventsapp.features.map.domain.MapRepo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.FusedOrientationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
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