package com.akmaldev.tutorapp.di

import com.akmaldev.tutorapp.data.repository.auth.AuthRepository
import com.akmaldev.tutorapp.data.repository.auth.impl.AuthRepositoryImpl
import com.akmaldev.tutorapp.data.repository.main.MainRepository
import com.akmaldev.tutorapp.data.repository.main.impl.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @[Binds Singleton]
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @[Binds Singleton]
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}