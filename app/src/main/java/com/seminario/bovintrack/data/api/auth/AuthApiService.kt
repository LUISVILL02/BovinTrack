package com.seminario.bovintrack.data.api.auth

import com.seminario.bovintrack.data.dto.auth.LoginDto
import com.seminario.bovintrack.data.dto.auth.RegisterDto
import com.seminario.bovintrack.data.dto.auth.ResponseLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body loginDto: LoginDto): Response<ResponseLogin>

    @POST("auth/register")
    suspend fun register(@Body registerDto: RegisterDto): Response<String>

}