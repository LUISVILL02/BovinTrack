package com.seminario.bovintrack.domain.repository

import android.util.Log
import com.seminario.bovintrack.data.api.propietario.BovinoService
import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import java.util.UUID
import javax.inject.Inject

class BovinoRepository @Inject constructor(
    private val bovinoService: BovinoService
) {
    suspend fun getBovinosByPropietario(idPropietario: UUID) : Result<List<BovinoDto>> {
        return try {
            val response = bovinoService.getBovinosByPropietario(idPropietario)
            if (response.isSuccessful) {
                Log.d("BovinoRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("BovinoRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBovinosByCapataz(idCapataz: UUID) : Result<List<BovinoDto>> {
        return try {
            val response = bovinoService.getBovinosByCapataz(idCapataz)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getBovinoById(idBovino: String) : Result<BovinoDto> {
        return try {
            val response = bovinoService.getBovinoById(idBovino)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createBovino(bovino: BovinoDto) : Result<BovinoDto> {
        return try {
            val response = bovinoService.createBovino(bovino)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}