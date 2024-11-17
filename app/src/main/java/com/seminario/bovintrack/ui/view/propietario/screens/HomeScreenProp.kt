package com.seminario.bovintrack.ui.view.propietario.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import com.seminario.bovintrack.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenProp(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val user by userViewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.loadUserFromToken()
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
                user?.let {
                    Text(text = "Bienvenido ${it.nombre} usted es un: ${it.roles}")
                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(
                        onClick = {
                            val route = if (it.roles == "PROPIETARIO") {
                                NavigationItem.Fincas.route
                            } else {
                                NavigationItem.Finca.createRoute(it.id)
                            }
                            navController.navigate(route)
                        }
                    ) {
                        Text(text = if (it.roles == "PROPIETARIO") "Lista de fincas" else "Finca")
                    }
                } ?: run {
                    CircularProgressIndicator()
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Button(
                    onClick = {
                        navController.navigate(NavigationItem.ListBovi.route)
                    }
                ) {
                    Text(text = "Lista de bovinos")
                }
            }
        }
    )
}