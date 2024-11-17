package com.seminario.bovintrack.data.api.propietario

import com.seminario.bovintrack.data.dto.propietario.PotreroDto
import com.seminario.bovintrack.data.dto.propietario.save.PotreroDtoSave
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface PotreroApiService {
    @GET("potrero/{idFinca}")
    suspend fun getPotreroById(@Path("idFinca") idFinca: UUID): Response<PotreroDto>

    @POST("potrero/{idFinca}")
    suspend fun createPotrero(@Path("idFinca") idFinca: UUID, potrero: PotreroDtoSave): Response<PotreroDto>

}