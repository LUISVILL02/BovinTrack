package com.seminario.bovintrack.data.api.propietario

import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import com.seminario.bovintrack.data.dto.propietario.HistorialUbiDto
import com.seminario.bovintrack.data.dto.propietario.save.BovinoDtoSave
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
    suspend fun createBovino(bovino: BovinoDtoSave): Response<BovinoDto>

    @GET("historial/{idBovino}")
    suspend fun getHistorialUbi(@Path("idBovino") idBovino: String): Response<List<HistorialUbiDto>>

}