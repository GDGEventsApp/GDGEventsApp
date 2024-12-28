package com.gdgevents.gdgeventsapp

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute(val route: String) {
    object Home : AppRoute("home_route")
    object MyAgenda : AppRoute("agenda_route")
    object Favorite : AppRoute("favorite_route")
    object Settings : AppRoute("settings_route")
}
