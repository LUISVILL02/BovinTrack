package com.seminario.bovintrack.ui.navigate

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.seminario.bovintrack.ui.view.SplashScreen
import com.seminario.bovintrack.ui.view.auth.LoginScreen

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
            //RegisterScreen()
            Text("Register")
        }
        composable(NavigationItem.Home.route){
            //HomeScreen()
            Text("Home")
        }

    }
}