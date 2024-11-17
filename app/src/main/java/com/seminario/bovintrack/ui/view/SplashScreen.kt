package com.seminario.bovintrack.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.seminario.bovintrack.ui.navigate.NavigationItem
import com.seminario.bovintrack.ui.viewmodel.TopBarViewModel
import com.seminario.bovintrack.ui.viewmodel.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    topBarViewModel: TopBarViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val token by topBarViewModel.isToken.collectAsState()
    val user by userViewModel.user.collectAsState()
    LaunchedEffect(key1 = true) {
        userViewModel.loadUserFromToken()
        delay(1000)
        if (!token) navController.navigate(NavigationItem.Login.route) {
            popUpTo(NavigationItem.Login.route) { inclusive = true }
        }
        else {
            user?.let {
                if (it.roles == "ADMIN") {
                    navController.navigate(NavigationItem.AdminHome.route)
                } else {
                    navController.navigate(NavigationItem.Home.route)
                }
            }
        }
    }
    Column(
        modifier = modifier.fillMaxSize()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "BovinTrack")
    }
}