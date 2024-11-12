package com.seminario.bovintrack.ui.view.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seminario.bovintrack.data.dto.auth.RegisterDto
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.viewmodel.auth.RegisterViewModel

@Composable
fun FormRegister(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
){
    val feedback by registerViewModel.feedback.collectAsState("")
    val registerStatus by registerViewModel.status.collectAsState(false)
    val isChecked by registerViewModel.isChecked.collectAsState()
    val nombre by registerViewModel.nombre.collectAsState("")
    val apellido by registerViewModel.apellido.collectAsState("")
    val identificacion by registerViewModel.identificacion.collectAsState("")
    val correo by registerViewModel.correo.collectAsState("")
    val contraseña by registerViewModel.contraseña.collectAsState("")
    val propietario by registerViewModel.propietario.collectAsState("")

    Column (modifier = Modifier.fillMaxSize()){
        Text(text = "Registrate en la aplicaciión")
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked -> registerViewModel.onCheckedChange(isChecked) },
            )
            Text(text = "Registrar como capataz")
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            TextField(
                modifier = Modifier.weight(1f),
                value = nombre,
                onValueChange = {nombre -> registerViewModel.setNombre(nombre)},
                label = { Text("Nombre") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.weight(1f),
                value = apellido,
                onValueChange = {apellido -> registerViewModel.setApellido(apellido)},
                label = { Text("Apellido") }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = identificacion,
            onValueChange = {iden -> registerViewModel.setIdentificacion(iden)},
            label = { Text("Identificación") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = correo,
            onValueChange = {correo -> registerViewModel.setCorreo(correo)},
            label = { Text("Correo") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = contraseña,
            onValueChange = {contraseña -> registerViewModel.setContraseña(contraseña)},
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        if(isChecked){
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = propietario,
                onValueChange = {propietario -> registerViewModel.setPropietario(propietario)},
                label = { Text("Correo de tu propietario asignado") }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                registerViewModel.register(RegisterDto(nombre, apellido,
                    identificacion, correo, contraseña, propietario,
                    if(isChecked) "CAPATAZ" else "PROPIETARIO"))
            }) {
            Text(text = "Registrarse")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$feedback")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(NavigationItem.Login.route)
            },
            modifier = Modifier.fillMaxWidth(),

        ) {
            Text(text = "Ya tienes cuenta? Inicia sesión")
        }

        if(registerStatus){
            navController.navigate(NavigationItem.Login.route)
        }
    }
}