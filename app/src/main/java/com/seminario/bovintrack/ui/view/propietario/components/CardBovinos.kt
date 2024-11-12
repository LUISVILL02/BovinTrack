package com.seminario.bovintrack.ui.view.propietario.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import java.time.format.DateTimeFormatter

@Composable
fun CardBovinos(
    bovino: BovinoDto,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Código del Bovino
            Text(
                text = "Código: ${bovino.codigo}",
            )

            // Fecha de Nacimiento
            Text(
                text = "Fecha de Nacimiento: ${bovino.fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}",
                modifier = Modifier.padding(top = 4.dp)
            )

            // Fecha de Ingreso
            Text(
                text = "Fecha de Ingreso: ${bovino.fechaIngreso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}",
                modifier = Modifier.padding(top = 4.dp)
            )

            // Fecha de Salida
            Text(
                text = "Fecha de Salida: ${bovino.fechaSalida?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "N/A"}",
                modifier = Modifier.padding(top = 4.dp)
            )

            // Sexo
            Text(
                text = "Sexo: ${bovino.sexo}",
                modifier = Modifier.padding(top = 4.dp)
            )

            // Peso Entrada
            Text(
                text = "Peso de Entrada: ${bovino.pesoEntrada}",
                modifier = Modifier.padding(top = 4.dp)
            )

            // Color
            Text(
                text = "Color: ${bovino.color}",
                modifier = Modifier.padding(top = 4.dp)
            )

        }
    }
}