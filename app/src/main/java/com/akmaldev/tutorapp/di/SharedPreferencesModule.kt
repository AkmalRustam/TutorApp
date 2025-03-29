package com.akmaldev.tutorapp.di

import android.content.Context
import android.content.SharedPreferences
import com.akmaldev.tutorapp.util.constant.AppConstants.SHARED_PREFERENCES
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideSharedPreferencesHelper(sharedPreferences: SharedPreferences): SharedPreferencesHelper =
        SharedPreferencesHelper(sharedPreferences)
}