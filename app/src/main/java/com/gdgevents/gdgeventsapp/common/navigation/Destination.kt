package com.gdgevents.gdgeventsapp.common.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    val route: String

    // add Destinations

    @Serializable
    data object OnboardingScreen : Destination {
        override val route: String = "onboardingScreen"
    }
    @Serializable
    data object EnterLocationScreen : Destination {
        override val route: String = "enterLocationScreen"
    }

    @Serializable
    data object HomeScreen : Destination {
        override val route: String = "homeScreen"
    }
}

