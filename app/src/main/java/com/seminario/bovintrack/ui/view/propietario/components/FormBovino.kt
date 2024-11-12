package com.seminario.bovintrack.ui.view.propietario.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun FormBovino(
    navController: NavController,
) {
    TextField(
        value = "",
        onValueChange = { },
        label = {
            Text("Código")
        }
    )
}