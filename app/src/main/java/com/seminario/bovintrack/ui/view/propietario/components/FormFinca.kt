package com.seminario.bovintrack.ui.view.propietario.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.seminario.bovintrack.data.dto.propietario.save.FincaDtoSave
import com.seminario.bovintrack.ui.viewmodel.FIncaViewModel
import com.seminario.bovintrack.ui.viewmodel.UserViewModel

@Composable
fun FormFinca(
    navController: NavController,
    fincaViewModel: FIncaViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {

    var ubicar by remember { mutableStateOf(false) }

    val user by userViewModel.user.collectAsState()

    val nombre by fincaViewModel.nombre.collectAsState("")
    val numeroPotreros by fincaViewModel.numeroPotreros.collectAsState("")
    val correoCapataz by fincaViewModel.correoCapataz.collectAsState("")
    val latitud by fincaViewModel.latitud.collectAsState("")
    val longitud by fincaViewModel.longitud.collectAsState("")

    val statusCreate by fincaViewModel.statusCreate.collectAsState()
    val feedback by fincaViewModel.feedback.collectAsState()

    val isFormValid = listOf(numeroPotreros, nombre, correoCapataz).all { it.isNotEmpty() }

    LaunchedEffect(Unit) {
        userViewModel.loadUserFromToken()
    }

    val positionD = LatLng(11.0, -74.0)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(positionD, 5f)
    }
    Column (
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ){
        Text(text = "Registrar finca")
        Spacer(modifier = Modifier.padding(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = {nombre -> fincaViewModel.setNombre(nombre)},
            label = { Text("Nombre") },
            isError = nombre.isEmpty()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = numeroPotreros,
            onValueChange = {numPo -> fincaViewModel.setNumeroPotreros(numPo)},
            label = { Text("Numero de potreros") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = numeroPotreros.isEmpty()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Card(
            modifier = Modifier.wrapContentSize()
        ) {
            Column {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(text = "Ubicacion de la finca")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(onClick = {
                        ubicar = !ubicar
                        Log.d("ubicar", "${ubicar}")
                    }) {
                        Text(text = "Ubicar")
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
                if (ubicar){
                    Text(text = "Ubica la finca en el mapa")
                }
                if (ubicar){
                    GoogleMap(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        cameraPositionState = cameraPositionState,
                        onMapClick = { latLng ->
                            fincaViewModel.setLatitud(latLng.latitude)
                            fincaViewModel.setLongitud(latLng.longitude)
                            ubicar = false
                            Log.d("MapClick", "Latitud: ${latLng.latitude}, Longitud: ${latLng.longitude}")
                        },
                    ) {

                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = correoCapataz,
            onValueChange = {coreo -> fincaViewModel.setCorreoCapataz(coreo)},
            label = { Text("Correo del capataz") },
            isError = correoCapataz.isEmpty()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid,
            onClick = {
                user?.let {
                    fincaViewModel.createFinca(
                        fincaDto = FincaDtoSave(
                            nombre = nombre,
                            numeroPotreros = (Integer.parseInt(numeroPotreros)),
                            latitud = latitud.toString().toDouble(),
                            longitud = longitud.toString().toDouble(),
                            correoCapataz = correoCapataz
                        ),
                        idPropietario = it.id
                    )
                }
            }) {
            Text(text = "Guardar finca")
        }
        Spacer(modifier = Modifier.padding(16.dp))
        if (!isFormValid){
            Text(text = "Todos los campos son obligatorios")
        }
        Spacer(modifier = Modifier.padding(16.dp))
        if (statusCreate){
            Text(text = "Finca creada con exito")
            navController.popBackStack()
        }else{
            Log.d("Feedback", "${feedback}")
            Text(text = feedback)
        }
    }
}