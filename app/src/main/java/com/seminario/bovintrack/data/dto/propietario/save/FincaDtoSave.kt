package com.seminario.bovintrack.data.dto.propietario.save

import com.google.gson.annotations.SerializedName

data class FincaDtoSave(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("numeroPotreros") val numeroPotreros: Int,
    @SerializedName("longitud") val longitud: Double,
    @SerializedName("latitud") val latitud: Double,
    @SerializedName("correoCapataz") val correoCapataz: String,
)
