package com.seminario.bovintrack.ui.view.propietario.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.seminario.bovintrack.data.dto.propietario.save.BovinoDtoSave
import com.seminario.bovintrack.ui.viewmodel.DetailBoviViewModel
import com.seminario.bovintrack.ui.viewmodel.UserViewModel
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBovino(
    navController: NavController,
    detailBoviViewModel: DetailBoviViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val user = userViewModel.user.collectAsState()

    var fechaNacimiento = detailBoviViewModel.fechaNacimiento.collectAsState().value
    var fechaIngreso = detailBoviViewModel.fechaIngreso.collectAsState().value
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val pesoEntrada = detailBoviViewModel.peso.collectAsState()
    val sexo = detailBoviViewModel.sexo.collectAsState()
    val color = detailBoviViewModel.color.collectAsState()
    val idSensor = detailBoviViewModel.sensor.collectAsState()
    val idPotrero = detailBoviViewModel.potrero.collectAsState()

    var chetH by remember { mutableStateOf(false) }
    var chetM by remember { mutableStateOf(false) }

    val isFormValid = fechaNacimiento != null && fechaIngreso != null && pesoEntrada.value != null && sexo.value != null && color.value != null && idSensor.value != null && idPotrero.value != null

    val statusCreate = detailBoviViewModel.statusCreate.collectAsState()
    val feedback = detailBoviViewModel.feedback.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.loadUserFromToken()
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
            value = fechaNacimiento?.format(dateFormatter) ?: "",
            onValueChange = {it ->
                detailBoviViewModel.setFechaNacimiento(it)},
            label = { Text("Fecha de nacimiento") },
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = fechaIngreso?.format(dateFormatter) ?: "",
            onValueChange = {it ->
                detailBoviViewModel.setFechaIngreso(it)},
            label = { Text("Fecha de ingreso") },
            isError = false
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Column {
                Checkbox(
                    checked = chetM,
                    onCheckedChange = { chet ->
                        chetM = chet
                        detailBoviViewModel.setSexo("M") },
                )
                Text(text = "Macho")
            }
            Column {
                Checkbox(
                    checked = chetH,
                    onCheckedChange = { chet ->
                        chetH = chet
                        detailBoviViewModel.setSexo("H") },
                )
                Text(text = "Hembra")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = pesoEntrada.value ?: "",
            onValueChange = { peso -> detailBoviViewModel.setPeso(peso)},
            label = { Text("Peso de entrada") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = color.value ?: "",
            onValueChange = {color -> detailBoviViewModel.setColor(color)},
            label = { Text("Color") },
            isError = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = idSensor.value ?: "",
            onValueChange = {sensor -> detailBoviViewModel.setSensor(sensor)},
            label = { Text("Id del sensor") },
            isError = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = idPotrero.value ?: "",
            onValueChange = {potero -> detailBoviViewModel.setPotrero(potero)},
            label = { Text("Id del potrero") },
            isError = false
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            onClick = {
                user?.let {
                    detailBoviViewModel.createBovino(BovinoDtoSave(
                        fechaNacimiento = fechaNacimiento?.format(dateFormatter) ?: "",
                        fechaIngreso = fechaIngreso?.format(dateFormatter) ?: "",
                        sexo = sexo.value?.get(0) ?: 'M',
                        pesoEntrada = (BigDecimal(pesoEntrada.value ?: "0")),
                        color = color.value ?: "",
                        sensorId = (UUID.fromString(idSensor.value ?: "00000000-0000-0000-0000-000000000000")),
                        potreroId = (UUID.fromString(idPotrero.value ?: "00000000-0000-0000-0000-000000000000")),
                        imagen = "",
                        propietarioId = it.value?.id!!
                    ))
                }
            }) {
            Text(text = "Guardar finca")
        }
        Spacer(modifier = Modifier.padding(16.dp))
        if (!isFormValid){
            Text(text = "Todos los campos son obligatorios")
        }
        Spacer(modifier = Modifier.padding(16.dp))
        if (statusCreate.value){
            Text(text = "Finca creada con exito")
            navController.popBackStack()
        }else{
            Log.d("Feedback", "${feedback}")
            Text(text = feedback.value)
        }
    }
}