package com.seminario.bovintrack.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.seminario.bovintrack.data.dto.DataSensorDto
import com.seminario.bovintrack.ui.viewmodel.UserViewModel
import com.seminario.bovintrack.ui.viewmodel.WebSocketViewModel
import com.seminario.bovintrack.websocket.connectToStomp
import java.util.UUID

@Composable
fun OnlyUbiBoviScreen(
    bovinoId: String,
    sensorId: UUID,
    webSocketViewModel: WebSocketViewModel = hiltViewModel(),
    viewModel: UserViewModel = hiltViewModel(),
){

    val sensores by webSocketViewModel.sensores.collectAsState()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserFromToken()
    }
    if (user != null){
        connectToStomp(user!!, webSocketViewModel)
        Log.d("MapBovi", "Connected to Stomp")
    }

    val bovinoUbi: DataSensorDto? = sensores.get(sensorId)
    if (bovinoUbi == null){
        Log.d("OnlyUbiBoviScreen", "Sensor not found")
        Log.d("OnlyUbiBoviScreen", "Sensores activos: $sensores")
    }else{
        Log.d("OnlyUbiBoviScreen", "Sensor found: $bovinoUbi")
        Log.d("OnlyUbiBoviScreen", "Sensores activos: $sensores")
    }

    val positionD = LatLng(11.0, -74.0)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(positionD, 5f)
    }
    Column (
        modifier = Modifier.fillMaxSize()
            .fillMaxHeight()
    ){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            if (bovinoUbi != null) {
                Marker(
                    state = MarkerState(position = LatLng(bovinoUbi.latitud, bovinoUbi.longitud)),
                    title = "Sensor ${bovinoUbi.id}",
                    snippet = "Marker in Singapore",
                    contentDescription = "Ubicacion del bovino ${bovinoId}"
                )
            }
        }
    }
}