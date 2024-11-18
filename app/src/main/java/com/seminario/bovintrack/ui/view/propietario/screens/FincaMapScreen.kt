package com.seminario.bovintrack.ui.view.propietario.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import com.seminario.bovintrack.ui.viewmodel.PotreroViewModel
import java.util.UUID

@Composable
fun FincaMapScreen(
    navController: NavController,
    idFinca: UUID,
    potreroViewModel: PotreroViewModel = hiltViewModel()
) {
    val isSave by potreroViewModel.isSave.collectAsState()
    val potreros by potreroViewModel.potreros.collectAsState()
    val poligono by potreroViewModel.poligono.collectAsState()
    val isSavePotrero by potreroViewModel.isSavePotrero.collectAsState()

    LaunchedEffect(potreros) {
        potreroViewModel.getPotreroByIdFinca(idFinca)
        Log.d("Potreros", "Potreros de la finca: $idFinca $potreros")
    }

    val positionD = LatLng(11.0, -74.0)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(positionD, 5f)
    }
    Column (
        modifier = Modifier.fillMaxSize()
            .fillMaxHeight()
    ){
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            onClick = { potreroViewModel.savePoligono() }) {
            Text(
                text = if (isSave) "Cancelar Guardado" else "Guardar Polígono",
            )
        }
        if (isSave) {
            Button(
                onClick = { potreroViewModel.createPotrero(idFinca) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Finalizar y Guardar Polígono")
            }
            if (isSavePotrero) {
                navController.popBackStack()
            }
        }
        GoogleMap(
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                potreroViewModel.addPoint(latLng)
            },
        ) {
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

            if (isSave && poligono.isNotEmpty()) {
                Polygon(
                    points = poligono,
                    strokeColor = Color.Red,
                    fillColor = Color.Yellow.copy(alpha = 0.3f),
                    strokeWidth = 2f
                )
            }

        }

    }
}