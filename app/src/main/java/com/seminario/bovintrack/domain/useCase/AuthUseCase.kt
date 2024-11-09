package com.seminario.bovintrack.domain.useCase

import com.seminario.bovintrack.data.dto.auth.LoginDto
import com.seminario.bovintrack.data.dto.auth.ResponseLogin
import com.seminario.bovintrack.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(login: LoginDto) : Result<ResponseLogin> {
        return authRepository.login(login)
    }
}