package com.seminario.bovintrack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import com.seminario.bovintrack.data.dto.propietario.save.BovinoDtoSave
import com.seminario.bovintrack.domain.useCase.BovinoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailBoviViewModel @Inject constructor(
    private val bovinoUseCase: BovinoUseCase
) : ViewModel() {
    private val _bovino = MutableStateFlow<BovinoDto?>(null)
    val bovino: StateFlow<BovinoDto?> = _bovino

    private val _fechaNacimiento = MutableStateFlow<String?>("")
    val fechaNacimiento: StateFlow<String?> = _fechaNacimiento

    private val _fechaIngreso = MutableStateFlow<String?>("")
    val fechaIngreso: StateFlow<String?> = _fechaIngreso

    private val _peso = MutableStateFlow<String?>(null)
    val peso: StateFlow<String?> = _peso

    private val _sexo = MutableStateFlow<String?>(null)
    val sexo: StateFlow<String?> = _sexo

    private val _color = MutableStateFlow<String?>(null)
    val color: StateFlow<String?> = _color

    private val sensorId = MutableStateFlow<String?>(null)
    val sensor: StateFlow<String?> = sensorId

    private val _potreroId = MutableStateFlow<String?>(null)
    val potrero: StateFlow<String?> = _potreroId

    private val _statusCreate = MutableStateFlow<Boolean>(false)
    val statusCreate: StateFlow<Boolean> = _statusCreate

    private val _feedback = MutableStateFlow<String>("")
    val feedback: StateFlow<String> = _feedback

    fun setFechaNacimiento(fechaNacimiento: String){
        _fechaNacimiento.value = fechaNacimiento
    }
    fun setFechaIngreso(fechaIngreso: String){
        _fechaIngreso.value = fechaIngreso
    }
    fun setPeso(peso: String) {
        _peso.value = peso
    }
    fun setSexo(sexo: String) {
        _sexo.value = sexo
    }
    fun setColor(color: String) {
        _color.value = color
    }
    fun setSensor(sensor: String) {
        sensorId.value = sensor
    }
    fun setPotrero(potrero: String) {
        _potreroId.value = potrero
    }



    fun getBovinoById(idBovino: String){
        viewModelScope.launch {
            val response = bovinoUseCase.getBovinoById(idBovino)
            if (response.isSuccess) {
                _bovino.value = response.getOrNull()
            }else{
                _bovino.value = null
            }
        }
    }

    fun createBovino(bovino: BovinoDtoSave){
        viewModelScope.launch {
            Log.d("DetailBoviViewModel", "createBovino: $bovino")
            /*val response = bovinoUseCase.createBovino(bovino)
            if (response.isSuccess) {
                _statusCreate.value = true
                _feedback.value = "Bovino creado correctamente"
            }else{
                _statusCreate.value = false
                _feedback.value = "Error al crear el bovino"
            }*/
        }
    }
}