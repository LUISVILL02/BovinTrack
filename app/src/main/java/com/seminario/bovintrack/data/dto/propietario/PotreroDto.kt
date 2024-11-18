package com.seminario.bovintrack.data.dto.propietario

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class PotreroDto(
    @SerializedName("id") val id: UUID,
    @SerializedName("longitud") val longitud: Double,
    @SerializedName("latitud") val latitud: Double,
    @SerializedName("area") val area: Int,
    @SerializedName("coordenadas") val coordenadas: List<CoordenadasDto>,
)
