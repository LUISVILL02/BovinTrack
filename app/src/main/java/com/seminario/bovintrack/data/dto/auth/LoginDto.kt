package com.seminario.bovintrack.data.dto.auth

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @SerializedName("correo") val correo: String,
    @SerializedName("contraseña") val contraseña: String
)
