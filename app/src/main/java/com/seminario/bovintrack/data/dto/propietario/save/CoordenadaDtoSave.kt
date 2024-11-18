package com.seminario.bovintrack.data.dto.propietario.save

import com.google.gson.annotations.SerializedName

data class CoordenadaDtoSave(
    @SerializedName("latitud") val latitud: Double,
    @SerializedName("longitud") val longitud: Double,
)
