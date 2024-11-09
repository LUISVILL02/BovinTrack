package com.seminario.bovintrack.ui.viewmodel.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.auth.LoginDto
import com.seminario.bovintrack.data.preferences.TokenPreference
import com.seminario.bovintrack.domain.useCase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val dataStorage: TokenPreference
) : ViewModel() {
    private val _loginSta = MutableStateFlow(false)
    val status: StateFlow<Boolean> = _loginSta

    private val _feedback = MutableStateFlow("")
    val feedback: StateFlow<String> = _feedback

    private val _correo = MutableStateFlow("")
    val correo: StateFlow<String> = _correo

    fun setCorreo(correo: String) {
        _correo.value = correo
    }

    private val _contraseña = MutableStateFlow("")
    val contraseña: StateFlow<String> = _contraseña

    fun setContraseña(contraseña: String) {
        _contraseña.value = contraseña
    }

    fun login(credentials: LoginDto) {
        viewModelScope.launch {
            try {
                val response = authUseCase(credentials)
                if (response.isSuccess) {
                    _correo.value = credentials.correo
                    _contraseña.value = credentials.contraseña
                    _loginSta.value = true
                    dataStorage.saveToken(response.getOrNull()?.token!!)
                }else{
                    Log.d("LoginViewModel", "Error linea 45: $response")
                    _loginSta.value = false
                    _feedback.value = response.exceptionOrNull()?.message.toString()
                }
            }catch (e: Exception) {
                Log.e("LoginViewModel", "Error linea 49: ${e.message}")
                _loginSta.value = false
            }
        }
    }
}