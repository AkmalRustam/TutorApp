package com.akmaldev.tutorapp.di

import android.content.Context
import com.akmaldev.tutorapp.data.remote.interceptor.AuthInterceptor
import com.akmaldev.tutorapp.data.remote.service.auth.AuthService
import com.akmaldev.tutorapp.data.remote.service.main.MainService
import com.akmaldev.tutorapp.util.constant.AppConstants.AUTH_OK_HTTP_CLIENT
import com.akmaldev.tutorapp.util.constant.AppConstants.AUTH_RETROFIT
import com.akmaldev.tutorapp.util.constant.AppConstants.BASE_URL
import com.akmaldev.tutorapp.util.constant.AppConstants.DEFAULT_HTTP_REQUEST_TIMEOUT
import com.akmaldev.tutorapp.util.constant.AppConstants.MAIN_OK_HTTP_CLIENT
import com.akmaldev.tutorapp.util.constant.AppConstants.MAIN_RETROFIT
import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @[Provides Singleton]
    fun provideChucker(
        @ApplicationContext context: Context
    ): ChuckerInterceptor = ChuckerInterceptor.Builder(context).build()

    @[Provides Singleton]
    fun provideAuthInterceptor(
        sharedPreferencesHelper: SharedPreferencesHelper
    ): AuthInterceptor = AuthInterceptor(sharedPreferencesHelper)

    @[Provides Singleton Named(AUTH_OK_HTTP_CLIENT)]
    fun provideAuthOkHttpClient(
        chucker: ChuckerInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(DEFAULT_HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(DEFAULT_HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(chucker)
        .build()

    @[Provides Singleton Named(AUTH_RETROFIT)]
    fun provideAuthRetrofit(
        @Named(AUTH_OK_HTTP_CLIENT) okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @[Provides Singleton]
    fun provideAuthService(@Named(AUTH_RETROFIT) retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @[Provides Singleton Named(MAIN_OK_HTTP_CLIENT)]
    fun provideMainOkHttpClient(
        chucker: ChuckerInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(DEFAULT_HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(DEFAULT_HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(DEFAULT_HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(chucker)
        .addInterceptor(authInterceptor)
        .build()

    @[Provides Singleton Named(MAIN_RETROFIT)]
    fun provideMainRetrofit(
        @Named(MAIN_OK_HTTP_CLIENT) okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @[Provides Singleton]
    fun provideMainService(@Named(MAIN_RETROFIT) retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)

    @[Provides Singleton]
    fun provideGson(): Gson = Gson()
}

