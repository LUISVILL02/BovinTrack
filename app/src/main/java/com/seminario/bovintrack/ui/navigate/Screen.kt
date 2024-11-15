package com.seminario.bovintrack.ui.navigate

enum class Screen{
    SPLASH,
    LOGIN,
    REGISTER,
    HOME,
    FINCAS,
    MAP_BOVI,
    LIST_BOVI,
    ADD_BOVI,
    DETAIL_BOVI,
    ONLY_UBI_BOVI
}

sealed class NavigationItem(
    val route: String
) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object Home : NavigationItem(Screen.HOME.name)
    object Fincas : NavigationItem(Screen.FINCAS.name)
    object MapBovi : NavigationItem(Screen.MAP_BOVI.name)
    object ListBovi : NavigationItem(Screen.LIST_BOVI.name)
    object AddBovi : NavigationItem(Screen.ADD_BOVI.name)
    object DetailBovi : NavigationItem("${Screen.DETAIL_BOVI.name}/{bovinoId}") {
        fun createRoute(bovinoId: String) = "${Screen.DETAIL_BOVI.name}/$bovinoId"
    }
    object OnlyUbiBovi : NavigationItem("${Screen.ONLY_UBI_BOVI.name}/{bovinoId}") {
        fun createRoute(bovinoId: String) = "${Screen.ONLY_UBI_BOVI.name}/$bovinoId"
    }
}