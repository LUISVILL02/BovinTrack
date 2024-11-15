package com.seminario.bovintrack.ui.view

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
import com.seminario.bovintrack.data.dto.propietario.FincaDto
import com.seminario.bovintrack.data.dto.propietario.PotreroDto
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.view.auth.TopBar
import com.seminario.bovintrack.ui.view.propietario.components.CardFincas
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListFincas(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val finca1 = FincaDto(
        id = UUID.randomUUID(),
        nombre = "Delicias",
        numeroPotreros = 8,
        longitud = 20.576004,
        latitud = -84.420458,
        idPropietario = UUID.randomUUID(),
        potreros = setOf(
            PotreroDto(
                id = UUID.randomUUID(),
                longitud = 20.576004,
                latitud = -84.420458,
                area = 500
            ),
            PotreroDto(
                id = UUID.randomUUID(),
                longitud = 20.578004,
                latitud = -84.422458,
                area = 450
            )
        )
    )

    val finca2 = FincaDto(
        id = UUID.randomUUID(),
        nombre = "El Paraíso",
        numeroPotreros = 6,
        longitud = 19.576004,
        latitud = -85.420458,
        idPropietario = UUID.randomUUID(),
        potreros = setOf(
            PotreroDto(
                id = UUID.randomUUID(),
                longitud = 19.576004,
                latitud = -85.420458,
                area = 400
            ),
            PotreroDto(
                id = UUID.randomUUID(),
                longitud = 19.578004,
                latitud = -85.422458,
                area = 350
            )
        )
    )

// Lista de fincas
    val listaFincas = listOf(finca1, finca2)

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
                    items(listaFincas.size) { index ->
                        CardFincas(finca = listaFincas[index], navController = navController)
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
                    Text(text = "Agregar finca")
                }
            }
        }
    )
}