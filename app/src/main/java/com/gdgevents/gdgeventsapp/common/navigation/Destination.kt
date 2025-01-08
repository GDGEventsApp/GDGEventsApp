package com.gdgevents.gdgeventsapp.common.navigation

sealed interface Destination {
    // add Destinations
    val route:String
    data object OnBoarding:Destination {
        override val route: String
            get() = "onboarding_screen"
    }
    data object Home:Destination {
        override val route: String
            get() = "home_screen"
    }
}