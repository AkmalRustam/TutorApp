package com.akmaldev.tutorapp.data.remote.interceptor

import com.akmaldev.tutorapp.util.helper.SharedPreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().addHeader(
            "Authorization", "Bearer ${sharedPreferencesHelper.accessToken}"
        ) // Tokenni qo'shing
            .build()
        return chain.proceed(newRequest)
    }
}