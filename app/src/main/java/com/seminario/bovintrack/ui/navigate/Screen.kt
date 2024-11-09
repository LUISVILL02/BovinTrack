package com.seminario.bovintrack.ui.navigate

enum class Screen{
    SPLASH,
    LOGIN,
    REGISTER,
    HOME,
}

sealed class NavigationItem(
    val route: String
) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object Home : NavigationItem(Screen.HOME.name)
}