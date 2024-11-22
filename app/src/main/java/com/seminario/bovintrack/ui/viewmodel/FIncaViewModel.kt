package com.seminario.bovintrack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.propietario.FincaDto
import com.seminario.bovintrack.data.dto.propietario.save.FincaDtoSave
import com.seminario.bovintrack.domain.useCase.FincaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class FIncaViewModel @Inject constructor(
    private val fincaUseCase: FincaUseCase
) : ViewModel() {

    private val _fincaC = MutableStateFlow<FincaDto?>(null)
    val fincaC: StateFlow<FincaDto?> = _fincaC

    private val _fincaP = MutableStateFlow<FincaDto?>(null)
    val fincaP: StateFlow<FincaDto?> = _fincaP

    private val _finca = MutableStateFlow<FincaDto?>(null)
    val finca: StateFlow<FincaDto?> = _finca

    private val _statusCreate = MutableStateFlow<Boolean>(false)
    val statusCreate: StateFlow<Boolean> = _statusCreate

    private val _feedback = MutableStateFlow<String>("")
    val feedback: StateFlow<String> = _feedback

    private val _nombre = MutableStateFlow<String>("")
    val nombre: StateFlow<String> = _nombre

    private val _numeroPotreros = MutableStateFlow<String>("")
    val numeroPotreros: StateFlow<String> = _numeroPotreros

    private val _longitud = MutableStateFlow<Double>(0.0)
    val longitud: StateFlow<Double> = _longitud

    private val _latitud = MutableStateFlow<Double>(0.0)
    val latitud: StateFlow<Double> = _latitud

    private val  _correoCapataz = MutableStateFlow<String>("")
    val correoCapataz: StateFlow<String> = _correoCapataz

    fun setNombre(nombre: String) {
        _nombre.value = nombre
    }

    fun setNumeroPotreros(numeroPotreros: String) {
        _numeroPotreros.value = numeroPotreros
    }

    fun setLongitud(longitud: Double) {
        _longitud.value = longitud
    }

    fun setLatitud(latitud: Double) {
        _latitud.value = latitud
    }

    fun setCorreoCapataz(correoCapataz: String) {
        _correoCapataz.value = correoCapataz
    }

    fun getFincaByIdCapataz(idCapataz: UUID) {
        viewModelScope.launch {
            val response = fincaUseCase.getFincaByCapataz(idCapataz)
            if (response.isSuccess) {
                _fincaC.value = response.getOrNull()
                _finca.value = response.getOrNull()
            } else {
                _fincaC.value = null
                _finca.value = null
            }
        }
    }

    fun getFincaByIdPropietario(idFinca: UUID) {
        viewModelScope.launch {
            val response = fincaUseCase.getFincaByPropietario(idFinca)
            if (response.isSuccess) {
                _fincaP.value = response.getOrNull()
                _finca.value = response.getOrNull()
            } else {
                _fincaP.value = null
                _finca.value = null
            }
        }
    }

    fun createFinca(fincaDto: FincaDtoSave, idPropietario: UUID) {
        viewModelScope.launch {
            val response = fincaUseCase.createFinca(idPropietario, fincaDto)
            if (response.isSuccess) {
                _statusCreate.value = true
                _finca.value = response.getOrNull()
            } else {
                _statusCreate.value = false
                _finca.value = null
                Log.d("FincaViewModel", "Error: ${response.exceptionOrNull()}")
                _feedback.value = response.exceptionOrNull()?.message.toString()
            }
        }
    }
}