package com.seminario.bovintrack.data.dto.propietario.save

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class BovinoDtoSave(
    @SerializedName("fechaNacimiento") val fechaNacimiento: LocalDate,
    @SerializedName("fechaIngreso") val fechaIngreso: LocalDate,
    @SerializedName("sexo") val sexo: Char,
    @SerializedName("pesoEntrada") val pesoEntrada: BigDecimal,
    @SerializedName("imagen") val imagen: String?,
    @SerializedName("color") val color: String,
    @SerializedName("sensorId") val sensorId: UUID,
    @SerializedName("potreroId") val potreroId: UUID,
    @SerializedName("propietarioId") val propietarioId: UUID,
)
