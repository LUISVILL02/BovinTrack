package com.seminario.bovintrack.domain.useCase

import com.seminario.bovintrack.data.dto.PaginatedResponse
import com.seminario.bovintrack.data.dto.propietario.FincaDto
import com.seminario.bovintrack.data.dto.propietario.save.FincaDtoSave
import com.seminario.bovintrack.domain.repository.FincaRepository
import java.util.UUID
import javax.inject.Inject

class FincaUseCase @Inject constructor(
    private val fincaRepository: FincaRepository
) {
    suspend fun getFincasByPropietario(idPropietario: UUID) : Result<PaginatedResponse<FincaDto>> {
        return fincaRepository.getFincasByPropietario(idPropietario)
    }
    suspend fun createFinca(idPropietario: UUID, finca: FincaDtoSave) : Result<FincaDto> {
        return fincaRepository.createFinca(idPropietario, finca)
    }
    suspend fun asignarCapataz(idFinca: UUID, idCapataz: UUID) : Result<FincaDto> {
        return fincaRepository.asignarCapataz(idFinca, idCapataz)
    }
    suspend fun getFincaByCapataz(idCapataz: UUID) : Result<FincaDto> {
        return fincaRepository.getFincaByCapataz(idCapataz)
    }
    suspend fun getFincaByPropietario(idFinca: UUID) : Result<FincaDto> {
        return fincaRepository.getFincaByPropietario(idFinca)
    }

}