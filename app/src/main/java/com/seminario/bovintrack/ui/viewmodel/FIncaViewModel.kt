package com.seminario.bovintrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.propietario.FincaDto
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

    fun getFincaByIdCapataz(idCapataz: UUID) {
        viewModelScope.launch {
            val response = fincaUseCase.getFincaByCapataz(idCapataz)
            if (response.isSuccess) {
                _fincaC.value = response.getOrNull()
            } else {
                _fincaC.value = null
            }
        }
    }

    fun getFincaByIdPropietario(idFinca: UUID) {
        viewModelScope.launch {
            val response = fincaUseCase.getFincaByPropietario(idFinca)
            if (response.isSuccess) {
                _fincaP.value = response.getOrNull()
            } else {
                _fincaP.value = null
            }
        }
    }
}