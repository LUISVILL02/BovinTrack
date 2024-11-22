package com.seminario.bovintrack.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.view.auth.TopBar
import com.seminario.bovintrack.ui.viewmodel.DetailBoviViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBoviScreen(
    navController: NavController,
    bovinoId: String,
    detailBoviViewModel: DetailBoviViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val bovino by detailBoviViewModel.bovino.collectAsState()

    LaunchedEffect(bovinoId) {
        detailBoviViewModel.getBovinoById(bovinoId)
    }

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Detalle del bovino con codigo: $bovinoId")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Nombre: ${bovino?.imagen}")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Sexo: ${bovino?.sexo}")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Peso entrada: ${bovino?.pesoEntrada}")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Fecha ingreso: ${bovino?.fechaIngreso}")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Fecha nacimiento: ${bovino?.fechaNacimiento}")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Id potrero: ${bovino?.idPotrero}")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "codigo del sensor: ${bovino?.codigoSensor}")
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Propietario: ${bovino?.nombrePropietario}")
                Spacer(modifier = Modifier.padding(16.dp))
                Row {
                    Button(
                        onClick = {
                            navController.navigate(NavigationItem.OnlyUbiBovi.createRoute(bovinoId, bovino!!.codigoSensor))
                        }
                    ) {
                        Text(text = "Ver ubicación")
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(
                        onClick = {
                            navController.navigate(NavigationItem.Historial.createRoute(bovinoId))
                        }
                    ) {
                        Text(text = "Ubicaciones, historial")
                    }
                }
            }
        }
    )
}