package com.seminario.bovintrack.data.dto.auth

import com.google.gson.annotations.SerializedName

data class RegisterDto(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("identificación") val identificación: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("contraseña") val contraseña: String,
    @SerializedName("propietario") val propietario: String,
    @SerializedName("rol") val rol: String
)