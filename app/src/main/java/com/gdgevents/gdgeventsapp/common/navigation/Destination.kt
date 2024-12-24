package com.gdgevents.gdgeventsapp.common.navigation

sealed interface Destination {
    val route: String

    // add Destinations

    data object OnboardingScreen : Destination {
        override val route: String = "onboardingScreen"
    }

    data object EnterLocationScreen : Destination {
        override val route: String = "enterLocationScreen"
    }

    data object HomeScreen : Destination {
        override val route: String = "homeScreen"
    }
}