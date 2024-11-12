package com.seminario.bovintrack.utils

import android.util.Base64
import com.seminario.bovintrack.data.dto.auth.User
import org.json.JSONObject
import java.util.UUID
import javax.inject.Inject

class TokenDecode @Inject constructor() {
    fun decodeJwt(token: String): User? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return null

            val payload = parts[1]
            val decodedBytes = Base64.decode(payload, Base64.URL_SAFE)
            val decodedString = String(decodedBytes, Charsets.UTF_8)

            val json = JSONObject(decodedString)
            User(
                id = UUID.fromString(json.getString("id")),
                correo = json.getString("correo"),
                nombre = json.getString("nombre"),
                apellido = json.getString("apellido"),
                roles = json.getString("roles")
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}