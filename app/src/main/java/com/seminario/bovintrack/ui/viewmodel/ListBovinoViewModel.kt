package com.seminario.bovintrack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import com.seminario.bovintrack.domain.useCase.BovinoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class ListBovinoViewModel @Inject constructor(
    private val bovinoUseCase: BovinoUseCase,
) : ViewModel() {

    private val _bovinosP = MutableStateFlow<List<BovinoDto>>(emptyList())
    val bovinosP: StateFlow<List<BovinoDto>> = _bovinosP

    private val _bovinosC = MutableStateFlow<List<BovinoDto>>(emptyList())
    val bovinosC: StateFlow<List<BovinoDto>> = _bovinosC

    fun getBovinosPorPropietario(idPropietario: UUID) {
        viewModelScope.launch {
            val response = bovinoUseCase.getBovinosByPropietario(idPropietario)
            if (response.isSuccess) {
                Log.d("ListBovinoViewModel", "Response successful: ${response.getOrNull()}")
                _bovinosP.value = response.getOrNull()!!
            }else{
                Log.e("ListBovinoViewModel", "Error linea 49: ${response.exceptionOrNull()?.message}")
                _bovinosP.value = emptyList()
            }
        }
    }

    fun getBovinosPorCapataz(idCapataz: UUID) {
        viewModelScope.launch {
            val response = bovinoUseCase.getBovinosByCapataz(idCapataz)
            if (response.isSuccess) {
                _bovinosC.value = response.getOrNull()!!
            }else{
                Log.e("ListBovinoViewModel", "Error: ${response.exceptionOrNull()?.message}")
                _bovinosC.value = emptyList()
            }
        }
    }

}