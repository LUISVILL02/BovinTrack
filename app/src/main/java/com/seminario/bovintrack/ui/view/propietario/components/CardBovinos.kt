package com.seminario.bovintrack.ui.view.propietario.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seminario.bovintrack.R
import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import com.seminario.bovintrack.ui.navigate.NavigationItem

@Composable
fun CardBovinos(
    bovino: BovinoDto,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate(NavigationItem.DetailBovi.createRoute(bovino.codigo))
            },
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Código del Bovino
            Text(
                text = "Código: ${bovino.codigo}",
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Image(
                painter = painterResource(id = R.drawable.bovinoimg),
                contentDescription = "Imagen del bovino",
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}