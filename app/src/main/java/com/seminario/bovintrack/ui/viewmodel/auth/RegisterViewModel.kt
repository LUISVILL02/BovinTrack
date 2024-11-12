package com.seminario.bovintrack.ui.viewmodel.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.auth.RegisterDto
import com.seminario.bovintrack.data.preferences.TokenPreference
import com.seminario.bovintrack.domain.useCase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel() {
    private val _regisSta = MutableStateFlow(false)
    val status: StateFlow<Boolean> = _regisSta

    private val _feedback = MutableStateFlow("")
    val feedback: StateFlow<String> = _feedback

    private val _isChecked = MutableStateFlow(false)
    val isChecked: StateFlow<Boolean> = _isChecked

    fun onCheckedChange(checked: Boolean) {
        _isChecked.value = checked
    }

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

    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre

    fun setNombre(nombre: String) {
        _nombre.value = nombre
    }

    private val _apellido = MutableStateFlow("")
    val apellido: StateFlow<String> = _apellido

    fun setApellido(apellido: String) {
        _apellido.value = apellido
    }

    private val _identificacion = MutableStateFlow("")
    val identificacion: StateFlow<String> = _identificacion

    fun setIdentificacion(identificacion: String) {
        _identificacion.value = identificacion
    }

    private val _propietario = MutableStateFlow("")
    val propietario: StateFlow<String> = _propietario

    fun setPropietario(propietario: String) {
        _propietario.value = propietario
    }


    fun register(credentials: RegisterDto) {
        viewModelScope.launch {
            try {
                val response = authUseCase(credentials)
                Log.d("Respuesta: registerViewmodel", "Response: $response")
                if (response.isSuccess) {
                    _regisSta.value = true
                    _feedback.value = "Usuario registrado con éxito"
                }else{
                    Log.d("RegisterViewModel", "Error linea 45: $response")
                    _regisSta.value = false
                    _feedback.value = response.exceptionOrNull()?.message.toString()
                }
            }catch (e: Exception) {
                Log.e("RegisterViewModel", "Error linea 49: ${e.message}")
                _regisSta.value = false
            }
        }
    }

}