package com.gdgevents.gdgeventsapp.user.di

import com.gdgevents.gdgeventsapp.user.data.UserRepoImpl
import com.gdgevents.gdgeventsapp.user.domain.UserRepo
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