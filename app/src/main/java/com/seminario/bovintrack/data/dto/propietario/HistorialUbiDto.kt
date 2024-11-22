package com.seminario.bovintrack.data.dto.propietario

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class HistorialUbiDto(
    @SerializedName("id") val id: UUID,
    @SerializedName("latitud") val latitud: Double,
    @SerializedName("longitud") val longitud: Double,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("codigoBovino") val codigoBovino: String,
    @SerializedName("idSensor") val idSensor: UUID,
)
