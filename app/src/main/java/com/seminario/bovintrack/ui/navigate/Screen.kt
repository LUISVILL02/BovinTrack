package com.seminario.bovintrack.ui.navigate

import java.util.UUID

enum class Screen{
    SPLASH,
    LOGIN,
    REGISTER,
    HOME,
    FINCAS,
    CREATE_FINCA,
    FINCA,
    MAP_FINCA,
    MAP_BOVI,
    LIST_BOVI,
    ADD_BOVI,
    DETAIL_BOVI,
    ONLY_UBI_BOVI,
    ADMIN_HOME,
    HISTORIAL
}

sealed class NavigationItem(
    val route: String
) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Register : NavigationItem(Screen.REGISTER.name)
    object Home : NavigationItem(Screen.HOME.name)
    object Fincas : NavigationItem(Screen.FINCAS.name)
    object CreateFinca : NavigationItem(Screen.CREATE_FINCA.name)
    object Finca : NavigationItem("${Screen.FINCA.name}/{fincaId}") {
        fun createRoute(fincaId: UUID) = "${Screen.FINCA.name}/$fincaId"
    }
    object MapFinca : NavigationItem("${Screen.MAP_FINCA.name}/{fincaId}") {
        fun createRoute(fincaId: UUID) = "${Screen.MAP_FINCA.name}/$fincaId"
    }
    object MapBovi : NavigationItem(Screen.MAP_BOVI.name)
    object ListBovi : NavigationItem(Screen.LIST_BOVI.name)
    object AddBovi : NavigationItem(Screen.ADD_BOVI.name)
    object DetailBovi : NavigationItem("${Screen.DETAIL_BOVI.name}/{bovinoId}") {
        fun createRoute(bovinoId: String) = "${Screen.DETAIL_BOVI.name}/$bovinoId"
    }
    object OnlyUbiBovi : NavigationItem("${Screen.ONLY_UBI_BOVI.name}/bovino/{bovinoId}/{sensorId}") {
        fun createRoute(bovinoId: String, sensorId: UUID) = "${Screen.ONLY_UBI_BOVI.name}/bovino/$bovinoId/$sensorId"
    }
    object AdminHome : NavigationItem(Screen.ADMIN_HOME.name)
    object Historial : NavigationItem("${Screen.HISTORIAL.name}/{bovinoId}") {
        fun createRoute(bovinoId: String) = "${Screen.HISTORIAL.name}/$bovinoId"
    }
}