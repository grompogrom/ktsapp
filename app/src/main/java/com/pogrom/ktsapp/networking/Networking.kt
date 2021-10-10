package com.pogrom.ktsapp.networking

import com.pogrom.ktsapp.networking.auth.AuthConfig.USER_ACCESS_TOKEN
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.internal.addHeaderLenient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

object Networking {
    private val loggingInterceptor = HttpLoggingInterceptor {
        Timber.tag("Network").d(it)
    }
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okhttpClient = if (USER_ACCESS_TOKEN == "") {
        OkHttpClient.Builder().addNetworkInterceptor(
            loggingInterceptor
        ).build()
    }
        else{
            OkHttpClient.Builder().addNetworkInterceptor(
                loggingInterceptor
            ).addNetworkInterceptor(
                AuthTokenInterceptor()
            ).build()
    }


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okhttpClient)
        .build()

    val unsplashApiService: UnsplashApiService
        get() = retrofit.create()
}
