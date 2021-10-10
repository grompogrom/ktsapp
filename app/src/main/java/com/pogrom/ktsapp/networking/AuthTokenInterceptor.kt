package com.pogrom.ktsapp.networking

import com.pogrom.ktsapp.networking.auth.AuthConfig.USER_ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer $USER_ACCESS_TOKEN")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
