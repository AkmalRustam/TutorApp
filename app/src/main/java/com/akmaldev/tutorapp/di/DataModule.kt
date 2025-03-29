package com.akmaldev.tutorapp.di

import android.content.Context
import com.akmaldev.tutorapp.util.permission.CheckPermissions
import com.akmaldev.tutorapp.util.permission.RequestPermissions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @[Provides Singleton]
    fun provideCheckPermissions(@ApplicationContext context: Context): CheckPermissions = CheckPermissions(context)

    @[Provides Singleton]
    fun provideRequestPermissions(): RequestPermissions = RequestPermissions()
}