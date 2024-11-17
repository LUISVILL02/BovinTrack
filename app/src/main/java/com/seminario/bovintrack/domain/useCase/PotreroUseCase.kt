package com.seminario.bovintrack.domain.useCase

import com.seminario.bovintrack.data.dto.propietario.PotreroDto
import com.seminario.bovintrack.data.dto.propietario.save.PotreroDtoSave
import com.seminario.bovintrack.domain.repository.PotreroRepository
import java.util.UUID
import javax.inject.Inject

class PotreroUseCase @Inject constructor(
    private val potreroRepository: PotreroRepository
) {
    suspend fun getPotreroByIdFinca(idFinca: UUID) : Result<PotreroDto> {
        return potreroRepository.getPotreroById(idFinca)
    }
    suspend fun createPotrero(idFinca: String, potrero: PotreroDtoSave) : Result<PotreroDto> {
        return potreroRepository.createPotrero(UUID.fromString(idFinca), potrero)
    }
}