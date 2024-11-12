package com.seminario.bovintrack.data.dto.propietario

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class BovinoDto(
    @SerializedName("codigo") val codigo: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: LocalDate,
    @SerializedName("fechaIngreso") val fechaIngreso: LocalDate,
    @SerializedName("fechaSalida") val fechaSalida: LocalDate?,
    @SerializedName("sexo") val sexo: String,
    @SerializedName("pesoEntrada") val pesoEntrada: BigDecimal,
    @SerializedName("imagen") val imagen: String?,
    @SerializedName("color") val color: String,
    @SerializedName("idPotrero") val idPotrero: UUID,
    @SerializedName("codigoSensor") val codigoSensor: UUID,
)
