package com.seminario.bovintrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.seminario.bovintrack.data.dto.DataSensorDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WebSocketViewModel @Inject constructor() : ViewModel() {

    private val _sensores = MutableStateFlow<Map<UUID, DataSensorDto>>(emptyMap())
    val sensores: StateFlow<Map<UUID, DataSensorDto>> = _sensores

    fun addMensaje(sensorData: DataSensorDto){
        val updatedMap = _sensores.value.toMutableMap()
        updatedMap[sensorData.id] = sensorData
        _sensores.value = updatedMap
    }
}