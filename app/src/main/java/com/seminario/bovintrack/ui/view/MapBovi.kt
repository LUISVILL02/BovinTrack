package com.seminario.bovintrack.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seminario.bovintrack.ui.viewmodel.UserViewModel
import com.seminario.bovintrack.websocket.connectToStomp

@Composable
fun MapBovi(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserFromToken()
    }

    Log.d("MapBovi", "User received: $user")
    if (user != null){
        connectToStomp(user!!)
        Log.d("MapBovi", "Connected to Stomp")
    }
    else Log.d("MapBovi", "User is null")

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "El mapa de bovinos")
    }
}