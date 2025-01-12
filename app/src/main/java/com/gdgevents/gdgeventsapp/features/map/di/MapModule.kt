package com.gdgevents.gdgeventsapp.features.map.di

import android.content.Context
import android.location.Geocoder
import com.gdgevents.gdgeventsapp.features.map.data.MapRepoImpl
import com.gdgevents.gdgeventsapp.features.map.domain.MapRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
object MapModule {
    @Provides
    fun provideRepo(): MapRepo = MapRepoImpl()

    @Provides
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder =
        Geocoder(context, Locale.getDefault())
}