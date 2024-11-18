package com.seminario.bovintrack.data.dto.propietario.save

import com.google.gson.annotations.SerializedName

data class PotreroSave(
    @SerializedName("potrero") val potrero: PotreroDtoSave,
    @SerializedName("coordenadas") val coordenadas: List<CoordenadaDtoSave>,
)
