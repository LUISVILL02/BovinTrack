package com.seminario.bovintrack.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.seminario.bovintrack.ui.view.DetailBoviScreen
import com.seminario.bovintrack.ui.view.ListFincas
import com.seminario.bovintrack.ui.view.MapBovi
import com.seminario.bovintrack.ui.view.OnlyUbiBoviScreen
import com.seminario.bovintrack.ui.view.SplashScreen
import com.seminario.bovintrack.ui.view.admin.screens.HomeAdmin
import com.seminario.bovintrack.ui.view.auth.LoginScreen
import com.seminario.bovintrack.ui.view.auth.RegisterScreen
import com.seminario.bovintrack.ui.view.propietario.screens.AgregarBovinoScreen
import com.seminario.bovintrack.ui.view.propietario.screens.CrearFincaScreen
import com.seminario.bovintrack.ui.view.propietario.screens.FincaDetailsScreen
import com.seminario.bovintrack.ui.view.propietario.screens.FincaMapScreen
import com.seminario.bovintrack.ui.view.propietario.screens.HomeScreenProp
import com.seminario.bovintrack.ui.view.propietario.screens.ListaBovinos
import java.util.UUID

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
        composable(NavigationItem.Fincas.route){
            ListFincas(navController = navController)
        }
        composable(NavigationItem.CreateFinca.route){
            CrearFincaScreen(navController = navController)
        }
        composable(
            route = NavigationItem.Finca.route,
            arguments = listOf(navArgument("fincaId") { type = NavType.StringType })
        ) { backStackEntry ->
            val fincaIdString =  backStackEntry.arguments?.getString("fincaId") ?: ""
            val fincaId = try {
                UUID.fromString(fincaIdString)
            } catch (e: IllegalArgumentException) {
                null
            }
            FincaDetailsScreen(navController = navController, fincaId = fincaId!!)
        }

        composable(
            route = NavigationItem.MapFinca.route,
            arguments = listOf(navArgument("fincaId") { type = NavType.StringType })
        ) { backStackEntry ->
            val fincaIdString =  backStackEntry.arguments?.getString("fincaId") ?: ""
            val fincaId = try {
                UUID.fromString(fincaIdString)
            } catch (e: IllegalArgumentException) {
                null
            }
            FincaMapScreen(navController = navController, idFinca = fincaId!!)
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
        composable(
            route = NavigationItem.DetailBovi.route,
            arguments = listOf(navArgument("bovinoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bovinoId = backStackEntry.arguments?.getString("bovinoId") ?: ""
            DetailBoviScreen(navController = navController, bovinoId = bovinoId)
        }
        composable(
            route = NavigationItem.OnlyUbiBovi.route,
            arguments = listOf(navArgument("bovinoId") { type = NavType.StringType },
                navArgument("sensorId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bovinoId = backStackEntry.arguments?.getString("bovinoId") ?: ""
            val sensorIdString = backStackEntry.arguments?.getString("sensorId") ?: ""
            val sensorId = try {
                UUID.fromString(sensorIdString)
            } catch (e: IllegalArgumentException) {
                null
            }
            OnlyUbiBoviScreen(bovinoId, sensorId = sensorId!!)
        }
        composable(NavigationItem.AdminHome.route){
            HomeAdmin(navController = navController)
        }

    }
}