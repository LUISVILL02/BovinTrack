package com.seminario.bovintrack.ui.viewmodel.propietario

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
class ListFincasViewModel @Inject constructor(
    private val fincaUseCase: FincaUseCase
) : ViewModel()
{
    private val _fincas = MutableStateFlow<List<FincaDto>>(emptyList())
    val fincas: StateFlow<List<FincaDto>> = _fincas

    private val _createFinca = MutableStateFlow(false)
    val createFinca: StateFlow<Boolean> = _createFinca

    private val _asignarCapataz = MutableStateFlow(false)
    val asignarCapataz: StateFlow<Boolean> = _asignarCapataz

    fun getFincasPorPropietario(idPropietario: UUID) {
        viewModelScope.launch {
            val response = fincaUseCase.getFincasByPropietario(idPropietario)
            if (response.isSuccess) {
                _fincas.value = response.getOrNull()?.content ?: emptyList()
            }else{
                _fincas.value = emptyList()
            }
        }
    }

}