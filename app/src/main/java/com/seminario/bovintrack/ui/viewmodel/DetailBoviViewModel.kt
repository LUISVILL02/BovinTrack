package com.seminario.bovintrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seminario.bovintrack.data.dto.propietario.BovinoDto
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
}