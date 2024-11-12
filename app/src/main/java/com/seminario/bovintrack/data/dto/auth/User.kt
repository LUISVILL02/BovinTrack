package com.seminario.bovintrack.data.dto.auth

import java.util.UUID

data class User(
    val id: UUID,
    val correo: String,
    val nombre: String,
    val apellido: String,
    val roles: String
)
