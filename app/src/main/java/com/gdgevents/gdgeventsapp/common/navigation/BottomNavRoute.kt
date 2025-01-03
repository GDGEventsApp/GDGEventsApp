package com.gdgevents.gdgeventsapp.common.navigation

import kotlinx.serialization.Serializable

sealed interface BottomNavRoute {

    @Serializable
    data object HomeRoute : BottomNavRoute

    @Serializable
    data object MyAgendaRoute : BottomNavRoute

    @Serializable
    data object FavoriteRoute : BottomNavRoute

    @Serializable
    data object SettingsRoute : BottomNavRoute
}