package com.seminario.bovintrack.domain.useCase

import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import com.seminario.bovintrack.data.dto.propietario.HistorialUbiDto
import com.seminario.bovintrack.data.dto.propietario.save.BovinoDtoSave
import com.seminario.bovintrack.domain.repository.BovinoRepository
import java.util.UUID
import javax.inject.Inject

class BovinoUseCase @Inject constructor(
    private val bovinoRepository: BovinoRepository
) {
    suspend fun getBovinosByPropietario(idPropietario: UUID) : Result<List<BovinoDto>> {
        return bovinoRepository.getBovinosByPropietario(idPropietario)
    }
    suspend fun getBovinosByCapataz(idCapataz: UUID) : Result<List<BovinoDto>> {
        return bovinoRepository.getBovinosByCapataz(idCapataz)
    }
    suspend fun getBovinoById(idBovino: String) : Result<BovinoDto> {
        return bovinoRepository.getBovinoById(idBovino)
    }
    suspend fun createBovino(bovino: BovinoDtoSave) : Result<BovinoDto> {
        return bovinoRepository.createBovino(bovino)
    }
    suspend fun getHistorialUbi(idBovino: String) : Result<List<HistorialUbiDto>> {
        return bovinoRepository.getHistorialUbi(idBovino)
    }
}