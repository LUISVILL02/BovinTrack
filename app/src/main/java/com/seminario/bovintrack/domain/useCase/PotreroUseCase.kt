package com.seminario.bovintrack.domain.useCase

import com.seminario.bovintrack.data.dto.propietario.PotreroDto
import com.seminario.bovintrack.data.dto.propietario.save.CoordenadaDtoSave
import com.seminario.bovintrack.data.dto.propietario.save.PotreroDtoSave
import com.seminario.bovintrack.domain.repository.PotreroRepository
import java.util.UUID
import javax.inject.Inject

class PotreroUseCase @Inject constructor(
    private val potreroRepository: PotreroRepository
) {
    suspend fun getPotreroByIdFinca(idFinca: UUID) : Result<List<PotreroDto>> {
        return potreroRepository.getPotreroById(idFinca)
    }
    suspend fun createPotrero(idFinca: UUID,
                              potrero: PotreroDtoSave,
                              coordenas: List<CoordenadaDtoSave>
    ) : Result<PotreroDto> {
        return potreroRepository.createPotrero(idFinca, potrero, coordenas)
    }
}