package com.seminario.bovintrack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.seminario.bovintrack.data.dto.propietario.PotreroDto
import com.seminario.bovintrack.data.dto.propietario.save.CoordenadaDtoSave
import com.seminario.bovintrack.data.dto.propietario.save.PotreroDtoSave
import com.seminario.bovintrack.domain.useCase.PotreroUseCase
import com.seminario.bovintrack.utils.calculatePolygonAreaInHectares
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class PotreroViewModel @Inject constructor(
    private val potreroUseCase: PotreroUseCase
) : ViewModel() {

    val _poligono = MutableStateFlow<List<LatLng>>(emptyList())
    val poligono = _poligono

    val _potreros = MutableStateFlow<List<PotreroDto?>>(emptyList())
    val potreros: StateFlow<List<PotreroDto?>> = _potreros

    val _isSave = MutableStateFlow<Boolean>(false)
    val isSave: StateFlow<Boolean> = _isSave

    val _isSavePotrero = MutableStateFlow<Boolean>(false)
    val isSavePotrero: StateFlow<Boolean> = _isSavePotrero

    fun addPoint(latLng: LatLng) {
        if(isSave.value){
            val list = _poligono.value.toMutableList()
            list.add(latLng)
            _poligono.value = list
        }
    }

    fun savePoligono(){
        _isSave.value = !isSave.value
    }

    fun getPotreroByIdFinca(idFinca: UUID) {
        viewModelScope.launch {
            val response = potreroUseCase.getPotreroByIdFinca(idFinca)
            if (response.isSuccess) {
                Log.d("PotreroViewModel", "Potreros: ${response.getOrNull()}")
                _potreros.value = response.getOrNull()!!
            }
        }
    }

    fun createPotrero(idFinca: UUID) {
        viewModelScope.launch {
            val coordenadas: List<CoordenadaDtoSave> = _poligono.value.map { latLng ->
                CoordenadaDtoSave(
                    latitud = latLng.latitude,
                    longitud = latLng.longitude
                )
            }
            val potrero = PotreroDtoSave(
                area = calculatePolygonAreaInHectares(_poligono.value),
            )
            val response = potreroUseCase.createPotrero(idFinca, potrero, coordenadas)
            if (response.isSuccess) {
                _isSavePotrero.value = true
            }
        }
    }

}