package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.OnboardingRoute

fun NavGraphBuilder.onboardingRoute(appState: GdgAppState) {
    composable<OnboardingRoute> {
        OnBoardingScreen(appState = appState)
    }
}