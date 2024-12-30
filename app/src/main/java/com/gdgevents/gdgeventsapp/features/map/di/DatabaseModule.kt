package com.gdgevents.gdgeventsapp.features.map.di

import android.content.Context
import androidx.room.Room
import com.gdgevents.gdgeventsapp.features.map.data.db.AppDatabase
import com.gdgevents.gdgeventsapp.features.map.data.db.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // This ensures the module is installed in the Singleton component.
object DatabaseModule {
    @Provides
    @Singleton // Dagger Hilt Singleton annotation
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton // Dagger Hilt Singleton annotation
    fun provideLocationDao(appDatabase: AppDatabase): LocationDao {
        return appDatabase.locationDao()
    }
}