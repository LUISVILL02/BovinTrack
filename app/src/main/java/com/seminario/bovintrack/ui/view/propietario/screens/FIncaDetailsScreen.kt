package com.seminario.bovintrack.ui.view.propietario.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.view.auth.TopBar
import com.seminario.bovintrack.ui.viewmodel.FIncaViewModel
import com.seminario.bovintrack.ui.viewmodel.UserViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FincaDetailsScreen(
    navController: NavController,
    fincaId: UUID,
    fincaViewModel: FIncaViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val fincaC by fincaViewModel.fincaC.collectAsState()
    val fincaP by fincaViewModel.fincaP.collectAsState()
    val user by userViewModel.user.collectAsState()

    LaunchedEffect(user) {
        userViewModel.loadUserFromToken()
        user?.let {
            if (it.roles == "PROPIETARIO") {
                fincaViewModel.getFincaByIdPropietario(fincaId)
            }else{
                fincaViewModel.getFincaByIdCapataz(user!!.id)
            }
        }
    }

    val finca = (user?.roles == "PROPIETARIO").let {
        if (it) fincaP else fincaC
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
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Column (
                    modifier = Modifier
                        .padding(top = 30.dp)
                ){
                    Text(text = "Nombre: ${finca?.nombre}")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = "Propietario: ${finca?.nombrePropietario}")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = "Capataz: ${finca?.nombreCapataz}")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = "Finca: $fincaId")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = "Número de potreros: ${finca?.numeroPotreros}")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(text = "Ubicación: ${finca?.latitud}, ${finca?.longitud}")
                    Spacer(modifier = Modifier.padding(16.dp))
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = {
                        navController.navigate(NavigationItem.MapBovi.route)
                    }
                ) {
                    Text(text = "Ubicaciones de bovinos")
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = {
                        navController.navigate(NavigationItem.MapFinca.createRoute(fincaId))
                    }
                ) {
                    Text(text = "Potreros")
                }
            }
        }
    )


}