package com.seminario.bovintrack.data.api.propietario

import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface BovinoService {
    @GET("bovinos/propietario/{idPropietario}")
    suspend fun getBovinosByPropietario(@Path("idPropietario") idPropietario: UUID): Response<List<BovinoDto>>

    @GET("bovinos/capataz/{idCapataz}")
    suspend fun getBovinosByCapataz(@Path("idCapataz") idCapataz: UUID): Response<List<BovinoDto>>

    @GET("bovinos/{id}")
    suspend fun getBovinoById(@Path("id") idBovino: String): Response<BovinoDto>

    @POST("bovinos")
    suspend fun createBovino(bovino: BovinoDto): Response<BovinoDto>
}