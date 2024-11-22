package com.seminario.bovintrack.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import com.seminario.bovintrack.ui.viewmodel.FIncaViewModel
import com.seminario.bovintrack.ui.viewmodel.PotreroViewModel
import com.seminario.bovintrack.ui.viewmodel.UserViewModel
import com.seminario.bovintrack.ui.viewmodel.WebSocketViewModel
import com.seminario.bovintrack.websocket.connectToStomp

@Composable
fun MapBovi(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel(),
    webSocketViewModel: WebSocketViewModel = hiltViewModel(),
    potreroViewModel: PotreroViewModel = hiltViewModel(),
    fIncaViewModel: FIncaViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()
    val sensores by webSocketViewModel.sensores.collectAsState()

    val potreros by potreroViewModel.potreros.collectAsState()
    val finca by fIncaViewModel.finca.collectAsState()


    LaunchedEffect(user) {
        viewModel.loadUserFromToken()
        user?.id?.let {
            if (user?.roles == "PROPIETARIO") {
                fIncaViewModel.getFincaByIdPropietario(user?.id!!)
            } else {
                fIncaViewModel.getFincaByIdCapataz(user?.id!!)
            }
        } ?: run {
            Log.e("MapBovi", "Obteniendo la finca sin exito")
        }
        if (finca != null) {
            potreroViewModel.getPotreroByIdFinca(finca?.id!!)
        }
    }

    Log.d("MapBovi", "User received: $user")
    SideEffect {
        if (user != null) {
            connectToStomp(user!!, webSocketViewModel)
            Log.d("MapBovi", "Connected to Stomp")
        } else {
            Log.d("MapBovi", "User is null")
        }
    }

    LazyColumn {
        items(sensores.values.toList()) { sensor ->
            Log.d("MapBovi", "Sensor: $sensor")
        }
    }
    Spacer(modifier = Modifier.padding(16.dp))
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(11.0, -74.0), 5f)
        }
    ) {
        sensores.values.forEach { sensor ->
            Marker(
                state = MarkerState(position = LatLng(sensor.latitud, sensor.longitud)),
                title = "Sensor ${sensor.id}",
                snippet = "Lat: ${sensor.latitud}, Lon: ${sensor.longitud}"
            )
        }
        potreros.map { potrero ->
            potrero?.coordenadas?.let { coordenadas ->
                val points = coordenadas.map { coordenada ->
                    LatLng(coordenada.latitud, coordenada.longitud)
                }
                Log.d("FincaMapScreen", "Coordenadas: $points")
                if (points.isNotEmpty()) {
                    Polygon(
                        points = points,
                        strokeColor = Color.Red,
                        fillColor = Color.Blue,
                        strokeWidth = 2f
                    )
                    Log.d("FincaMapScreen", "Coordenadas asignadas a su potrero")
                } else {
                    Log.d("FincaMapScreen", "No hay coordenadas asignadas a su potrero")
                }
            }
        }
    }
}