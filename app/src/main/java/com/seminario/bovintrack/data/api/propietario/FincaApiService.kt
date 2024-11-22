package com.seminario.bovintrack.data.api.propietario

import com.seminario.bovintrack.data.dto.PaginatedResponse
import com.seminario.bovintrack.data.dto.propietario.FincaDto
import com.seminario.bovintrack.data.dto.propietario.save.FincaDtoSave
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface FincaApiService {
    @GET("finca/{idPropietario}/")
    suspend fun getFincasByPropietario(
        @Path("idPropietario") idPropietario: UUID,
        @Query("page") page: Int? = 0,
        @Query("size") size: Int? = 10,
    ): Response<PaginatedResponse<FincaDto>>

    @POST("finca/{idPropietario}")
    suspend fun createFinca(
        @Path("idPropietario") idPropietario: UUID,
        @Body finca: FincaDtoSave
    ): Response<FincaDto>

    @PUT("finca/{idFinca}/{idCapataz}")
    suspend fun asignarCapataz(
        @Path("idFinca") idFinca: UUID,
        @Path("idCapataz") idCapataz: UUID
    ): Response<FincaDto>

    @GET("finca/capataz/{idCapataz}")
    suspend fun fincaByCapataz(
        @Path("idCapataz") idCapataz: UUID
    ): Response<FincaDto>

    @GET("finca/{idFinca}")
    suspend fun fincaByPropietario(
        @Path("idFinca") idFinca: UUID
    ): Response<FincaDto>
}