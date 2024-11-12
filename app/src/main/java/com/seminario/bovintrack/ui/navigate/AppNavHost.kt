package com.seminario.bovintrack.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.seminario.bovintrack.ui.view.MapBovi
import com.seminario.bovintrack.ui.view.SplashScreen
import com.seminario.bovintrack.ui.view.auth.LoginScreen
import com.seminario.bovintrack.ui.view.auth.RegisterScreen
import com.seminario.bovintrack.ui.view.propietario.screens.AgregarBovinoScreen
import com.seminario.bovintrack.ui.view.propietario.screens.HomeScreenProp
import com.seminario.bovintrack.ui.view.propietario.screens.ListaBovinos

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(
            route = NavigationItem.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(
            route = NavigationItem.Login.route){
            LoginScreen(navController = navController)
        }
        composable(NavigationItem.Register.route){
            RegisterScreen(navController = navController)
        }
        composable(NavigationItem.Home.route){
            HomeScreenProp(navController = navController)
        }

        composable(NavigationItem.MapBovi.route){
            MapBovi(navController = navController)
        }

        composable(NavigationItem.ListBovi.route){
            ListaBovinos(navController = navController)
        }
        composable(NavigationItem.AddBovi.route){
            AgregarBovinoScreen(navController = navController)
        }
    }
}