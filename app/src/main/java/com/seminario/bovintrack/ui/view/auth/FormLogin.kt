package com.seminario.bovintrack.ui.view.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import com.seminario.bovintrack.data.dto.auth.LoginDto
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.viewmodel.auth.LoginViewModel

@Composable
fun FormLogin(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val loginStatus by loginViewModel.status.collectAsState(false)
    val correo by loginViewModel.correo.collectAsState("")
    val contraseña by loginViewModel.contraseña.collectAsState("")
    val feedback by loginViewModel.feedback.collectAsState("")

    Column (modifier = Modifier.fillMaxSize()){
        Text(text = "Bienvenido de vuelta")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = correo,
            onValueChange = {correo -> loginViewModel.setCorreo(correo)},
            label = { Text("Correo") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = contraseña,
            onValueChange = {contraseña -> loginViewModel.setContraseña(contraseña)},
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                loginViewModel.login(LoginDto(correo, contraseña))
            }) {
            Text(text = "Iniciar Sesión")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$feedback")

        if(loginStatus){
            navController.navigate(NavigationItem.Home.route)
        }
    }
}