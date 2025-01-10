package com.gdgevents.gdgeventsapp.common.navigation

sealed interface Destination {
    val route:String
    data object OnBoarding:Destination {
        override val route: String
            get() = "onboarding_screen"
    }
    data object Map:Destination {
        override val route: String
            get() = "map_screen"
    }

}