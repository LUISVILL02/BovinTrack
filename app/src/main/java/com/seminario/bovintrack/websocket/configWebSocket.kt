package com.seminario.bovintrack.websocket

import android.util.Log
import com.seminario.bovintrack.data.dto.auth.User
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

fun connectToStomp(user: User) {
    var client: WebSocketClient
    val uri = URI("ws://192.168.80.23:8090/ws-datos")
    client = object : WebSocketClient(uri) {
        override fun onOpen(handshakedata: ServerHandshake?) {
            Log.i("WebSocket", "Connected to STOMP server")
            send("CONNECT\naccept-version:1.2\nhost:localhost\n\n\u0000")

            send("SUBSCRIBE\ndestination:/topic/datos/${user.id}/\nid:sub-0\n\n\u0000")
            Log.d("WebSocket", "Subscribed to /topic/datos/${user.id}/")

        }

        override fun onMessage(message: String?) {
            Log.d("WebSocket","Message from server: $message")

            if (message != null && message.startsWith("CONNECTED")) {
                Log.i("WebSocket", "STOMP connection established")
                Log.d("WebSocket mensaje devuelto", message)
            }
        }

        override fun onClose(code: Int, reason: String?, remote: Boolean) {
            Log.e("WebSocket","Connection closed: $reason")
        }

        override fun onError(ex: Exception?) {
            Log.e("WebSocket","Error occurred: ${ex?.message}")
        }
    }
    client.connect()
}