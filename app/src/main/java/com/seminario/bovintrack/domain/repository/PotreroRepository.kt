package com.seminario.bovintrack.domain.repository

import android.util.Log
import com.seminario.bovintrack.data.api.propietario.PotreroApiService
import com.seminario.bovintrack.data.dto.propietario.CoordenadasDto
import com.seminario.bovintrack.data.dto.propietario.PotreroDto
import com.seminario.bovintrack.data.dto.propietario.save.CoordenadaDtoSave
import com.seminario.bovintrack.data.dto.propietario.save.PotreroDtoSave
import com.seminario.bovintrack.data.dto.propietario.save.PotreroSave
import java.util.UUID
import javax.inject.Inject


class PotreroRepository @Inject constructor(
    private val potreroApiService: PotreroApiService
) {
    suspend fun getPotreroById(idFinca: UUID): Result<List<PotreroDto>> {
        return try {
            val response = potreroApiService.getPotreroById(idFinca)
            Log.d("PotreroRepository", "PotreroSave: $response")
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

    suspend fun createPotrero(idFinca: UUID,
                              potrero: PotreroDtoSave,
                              coordenas: List<CoordenadaDtoSave>
                              ): Result<PotreroDto> {
        return try {
            val potreroSave = PotreroSave(potrero, coordenas)
            Log.d("PotreroRepository", "PotreroSave: $potreroSave")
            val response = potreroApiService.createPotrero(idFinca, potreroSave)
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