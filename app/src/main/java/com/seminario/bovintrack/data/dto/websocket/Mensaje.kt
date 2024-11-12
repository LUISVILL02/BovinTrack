package com.seminario.bovintrack.data.dto.websocket

import java.util.UUID

data class Mensaje (
    val id: UUID = UUID.randomUUID(),
    var latitud: Double = 0.0,
    var longitud: Double = 0.0
)