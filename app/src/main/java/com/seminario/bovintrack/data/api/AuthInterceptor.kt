package com.seminario.bovintrack.data.api

import com.seminario.bovintrack.data.preferences.TokenPreference
import okhttp3.Interceptor
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStorage: TokenPreference,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val token = dataStorage.tokenflow
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}