package com.seminario.bovintrack.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.seminario.bovintrack.data.dto.propietario.HistorialUbiDto
import com.seminario.bovintrack.domain.useCase.BovinoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HistorialViewModel @Inject constructor(
    private val bovinoUseCase: BovinoUseCase
) : ViewModel() {

    private val _listHistorial = MutableStateFlow<List<HistorialUbiDto>>(emptyList())
    val listHistorial: StateFlow<List<HistorialUbiDto>> = _listHistorial

    suspend fun getHistorialUbi(idBovino: String) {
        val result = bovinoUseCase.getHistorialUbi(idBovino)
        if (result.isSuccess) {
            Log.d("HistorialViewModel", "Response successful: ${result.getOrNull()}")
            _listHistorial.value = result.getOrNull()!!
        }
    }

}