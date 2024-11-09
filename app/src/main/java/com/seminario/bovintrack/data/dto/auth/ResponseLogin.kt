package com.seminario.bovintrack.data.dto.auth

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("token") val token: String,
)
