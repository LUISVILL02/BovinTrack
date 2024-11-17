package com.seminario.bovintrack.data.api

import android.util.Log
import com.seminario.bovintrack.data.preferences.TokenPreference
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStorage: TokenPreference,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val token = runBlocking { dataStorage.tokenflow.firstOrNull() }
        if (token != null && token.isNotEmpty()) {
            Log.d("AuthInterceptor", "Bearer token: $token")
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            return chain.proceed(request)
        } else {
            Log.d("AuthInterceptor", "No token found, skipping Authorization header")
            return chain.proceed(chain.request())
        }
    }
}