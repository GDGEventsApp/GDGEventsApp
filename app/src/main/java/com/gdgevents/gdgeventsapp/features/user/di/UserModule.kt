package com.gdgevents.gdgeventsapp.features.user.di

import com.gdgevents.gdgeventsapp.features.user.data.UserRepoImpl
import com.gdgevents.gdgeventsapp.features.user.domain.UserRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindsUserRepo(
        userRepoImpl: UserRepoImpl
    ): UserRepo

}