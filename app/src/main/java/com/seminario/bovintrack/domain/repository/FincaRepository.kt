package com.seminario.bovintrack.domain.repository

import android.util.Log
import com.seminario.bovintrack.data.api.propietario.FincaApiService
import com.seminario.bovintrack.data.dto.PaginatedResponse
import com.seminario.bovintrack.data.dto.propietario.FincaDto
import java.util.UUID
import javax.inject.Inject

class FincaRepository @Inject constructor(
    private val fincaApiService: FincaApiService
) {
    suspend fun getFincasByPropietario(idPropietario: UUID) : Result<PaginatedResponse<FincaDto>> {
        return try {
            val response = fincaApiService.getFincasByPropietario(idPropietario)
            if (response.isSuccessful) {
                Log.d("FincaRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("FincasRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Log.e("FincaRepository", "Exception: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun createFinca(idPropietario: UUID, finca: FincaDto): Result<FincaDto> {
        return try {
            val response = fincaApiService.createFinca(idPropietario, finca)
            if (response.isSuccessful) {
                Log.d("FincaRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("FincaRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun asignarCapataz(idFinca: UUID, idCapataz: UUID): Result<FincaDto> {
        return try {
            val response = fincaApiService.asignarCapataz(idFinca, idCapataz)
            if (response.isSuccessful) {
                Log.d("FincaRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("FincaRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFincaByCapataz(idCapataz: UUID): Result<FincaDto> {
        return try {
            val response = fincaApiService.fincaByCapataz(idCapataz)
            if (response.isSuccessful) {
                Log.d("FincaRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("FincaRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFincaByPropietario(idFinca: UUID): Result<FincaDto> {
        return try {
            val response = fincaApiService.fincaByPropietario(idFinca)
            if (response.isSuccessful) {
                Log.d("FincaRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("FincaRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}