package com.gdgevents.gdgeventsapp.common.navigation

import kotlinx.serialization.Serializable

sealed interface NestedRoute {

    @Serializable
    data object HomeRoute : NestedRoute

    @Serializable
    data object MyAgendaRoute : NestedRoute

    @Serializable
    data object FavoriteRoute : NestedRoute

    @Serializable
    data object SettingsRoute : NestedRoute
}