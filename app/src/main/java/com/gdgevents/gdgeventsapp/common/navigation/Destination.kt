package com.gdgevents.gdgeventsapp.common.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    // add Destinations

    @Serializable
    data object OnboardingRoute : Destination

    @Serializable
    data object EnterLocationRoute : Destination

    @Serializable
    data object HomeRoute : Destination
}

