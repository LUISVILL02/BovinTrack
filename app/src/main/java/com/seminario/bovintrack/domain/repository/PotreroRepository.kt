package com.seminario.bovintrack.domain.repository

import android.util.Log
import com.seminario.bovintrack.data.api.propietario.PotreroApiService
import com.seminario.bovintrack.data.dto.propietario.PotreroDto
import com.seminario.bovintrack.data.dto.propietario.save.PotreroDtoSave
import java.util.UUID
import javax.inject.Inject


class PotreroRepository @Inject constructor(
    private val potreroApiService: PotreroApiService
) {
    suspend fun getPotreroById(idFinca: UUID): Result<PotreroDto> {
        return try {
            val response = potreroApiService.getPotreroById(idFinca)
            if (response.isSuccessful) {
                Log.d("PotreroRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("PotreroRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Log.e("PotreroRepository", "Exception: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun createPotrero(idFinca: UUID, potrero: PotreroDtoSave): Result<PotreroDto> {
        return try {
            val response = potreroApiService.createPotrero(idFinca, potrero)
            if (response.isSuccessful) {
                Log.d("PotreroRepository", "Response successful: ${response.body()}")
                Result.success(response.body()!!)
            } else {
                Log.d("PotreroRepository", "Response unsuccessful: ${response.errorBody()?.string()}")
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            Log.e("PotreroRepository", "Exception: ${e.message}")
            Result.failure(e)
        }
    }
}