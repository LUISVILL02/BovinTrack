package com.seminario.bovintrack.ui.view.propietario.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seminario.bovintrack.data.dto.propietario.FincaDto
import com.seminario.bovintrack.ui.navigate.NavigationItem

@Composable
fun CardFincas(
    finca: FincaDto,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                //navController.navigate(NavigationItem.DetailBovi.createRoute(bovino.codigo))
            },
    ) {
        Column {
            // Nombre de la finca
            Text(text = "Nombre: ${finca.nombre}")
            // Número de potreros
            Text(text = "Número de potreros: ${finca.numeroPotreros}")
            // Longitud
            Text(text = "Longitud: ${finca.longitud}")
            // Latitud
            Text(text = "Latitud: ${finca.latitud}")
        }
    }
}