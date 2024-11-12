package com.seminario.bovintrack.domain.repository

import android.util.Log
import com.seminario.bovintrack.data.api.auth.AuthApiService
import com.seminario.bovintrack.data.dto.auth.LoginDto
import com.seminario.bovintrack.data.dto.auth.RegisterDto
import com.seminario.bovintrack.data.dto.auth.ResponseLogin
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApiService: AuthApiService) {
    suspend fun login(loginDto: LoginDto) : Result<ResponseLogin> {
        return try {
            val response = authApiService.login(loginDto)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(registerDto: RegisterDto) : Result<String> {
        return try {
            val response = authApiService.register(registerDto)
            Log.d("Respuesta: registerRepository", "Response: $response")
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Log.e("RegisterRepository", "Error: ${e.message}")
            Result.failure(e)
        }
    }

}