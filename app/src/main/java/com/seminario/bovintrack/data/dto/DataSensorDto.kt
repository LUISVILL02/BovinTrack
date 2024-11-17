package com.seminario.bovintrack.data.dto

import java.util.UUID

data class DataSensorDto(
    val id: UUID,
    val longitud: Double,
    val latitud: Double,
)
