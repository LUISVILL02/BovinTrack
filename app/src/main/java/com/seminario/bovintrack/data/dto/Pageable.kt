package com.seminario.bovintrack.data.dto

import com.google.gson.annotations.SerializedName

data class PaginatedResponse<T>(
    @SerializedName("content") val content: List<T>,
    @SerializedName("pageable") val pageable: Pageable
)

data class Pageable(
    @SerializedName("pageNumber") val pageNumber: Int,
    @SerializedName("pageSize") val pageSize: Int,
)
