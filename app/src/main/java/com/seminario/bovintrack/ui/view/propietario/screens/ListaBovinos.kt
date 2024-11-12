package com.seminario.bovintrack.ui.view.propietario.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.seminario.bovintrack.data.dto.propietario.BovinoDto
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.view.auth.TopBar
import com.seminario.bovintrack.ui.view.propietario.components.CardBovinos
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaBovinos(
    navController: NavController,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val bovinos = listOf(
        BovinoDto(
        codigo = "BOV12345",
        fechaNacimiento = LocalDate.of(2020, 5, 15),
        fechaIngreso = LocalDate.of(2022, 3, 10),
        fechaSalida = LocalDate.of(2023, 6, 30),
        sexo = "Macho",
        pesoEntrada = BigDecimal("250.5"),
        imagen = "https://example.com/image.jpg",
        color = "Marrón",
        idPotrero = UUID.randomUUID(),
        codigoSensor = UUID.randomUUID(),
        ),
        BovinoDto(
            codigo = "BOV12345",
            fechaNacimiento = LocalDate.of(2020, 5, 15),
            fechaIngreso = LocalDate.of(2022, 3, 10),
            fechaSalida = LocalDate.of(2023, 6, 30),
            sexo = "Macho",
            pesoEntrada = BigDecimal("250.5"),
            imagen = "https://example.com/image.jpg",
            color = "Marrón",
            idPotrero = UUID.randomUUID(),
            codigoSensor = UUID.randomUUID(),
        )
    )


    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(navController)
        },
        content = { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    items(bovinos.size) { index ->
                        CardBovinos(bovino = bovinos[index], navController = navController)
                        Spacer(modifier = Modifier.padding(16.dp))
                    }
                }

                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = {
                        navController.navigate(NavigationItem.AddBovi.route)
                    }
                ) {
                    Text(text = "Agregar bovino")
                }
            }
        }
    )
}