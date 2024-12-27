package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.Destination

fun NavGraphBuilder.onBoardingRoute() {
    composable<Destination.OnboardingRoute> {
        OnBoardingScreen()
    }
}