package com.seminario.bovintrack.ui.view.auth

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.viewmodel.TopBarViewModel
import com.seminario.bovintrack.ui.viewmodel.auth.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    topBarViewModel: TopBarViewModel = hiltViewModel(),
    authViewModel: LoginViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val token by topBarViewModel.isToken.collectAsState()

    println("valor de token: $token")

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            if (token) {
                IconButton(onClick = { navController.navigate(NavigationItem.Home.route) }) {
                    Icon(Icons.Filled.Home, contentDescription = null)
                }
            }
        },
        title = { Text("BovinTrack") },
        actions = {
            if (token) {
                Button(
                    onClick = {
                        authViewModel.logout()
                        navController.navigate(NavigationItem.Login.route)
                    }
                ) {
                    Text(text = "Cerrar sesion")
                }
            }
        },
        scrollBehavior = scrollBehavior,

    )
}