package com.seminario.bovintrack.data.dto.propietario

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class FincaDto(
    @SerializedName("id") val id: UUID,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("numeroPotreros") val numeroPotreros: Int,
    @SerializedName("longitud") val longitud: Double,
    @SerializedName("latitud") val latitud: Double,
    @SerializedName("idPropietario") val idPropietario: UUID,
    @SerializedName("potreros") val potreros: Set<PotreroDto>
)
