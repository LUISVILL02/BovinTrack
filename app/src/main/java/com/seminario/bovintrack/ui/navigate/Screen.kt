package com.seminario.bovintrack.ui.navigate

enum class Screen{
    SPLASH,
    LOGIN,
    REGISTER,
    HOME,
    MAP_BOVI,
    LIST_BOVI,
    ADD_BOVI
}

sealed class NavigationItem(
    val route: String
) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object Home : NavigationItem(Screen.HOME.name)
    object MapBovi : NavigationItem(Screen.MAP_BOVI.name)
    object ListBovi : NavigationItem(Screen.LIST_BOVI.name)
    object AddBovi : NavigationItem(Screen.ADD_BOVI.name)
}