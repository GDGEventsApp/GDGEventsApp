package com.gdgevents.gdgeventsapp.common.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.features.event.presentaion.details.detailRoute
import com.gdgevents.gdgeventsapp.features.event.presentaion.search.searchRoute
import com.gdgevents.gdgeventsapp.features.map.presentaion.mapRoute
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.onboardingRoute
import com.gdgevents.gdgeventsapp.features.settings.presentaion.about.aboutRoute
import com.gdgevents.gdgeventsapp.features.settings.presentaion.helpSupport.helpSupportRoute
import com.gdgevents.gdgeventsapp.features.settings.presentaion.privacyPolicy.privacyPolicyRoute
import com.gdgevents.gdgeventsapp.features.user.presentaion.contactUs.contactUsRoute
import com.gdgevents.gdgeventsapp.features.user.presentaion.feedback.feedbackRoute

@Composable
fun MainNavHost(
    appState: GdgAppState,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = appState.mainNavController,
        startDestination = appState.mainStartDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400))
        },
        exitTransition = {
            scaleOut(
                targetScale = 0.98f,
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
        },
        popEnterTransition = {
            scaleIn(
                initialScale = 0.98f,
                animationSpec = tween(400)
            ) + fadeIn(animationSpec = tween(400))
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            ) + fadeOut(tween(500))
        },
        modifier = modifier,
    ) {
        onboardingRoute(appState = appState)
        mapRoute(appState = appState)
        searchRoute(appState = appState)
        detailRoute(appState = appState)
        aboutRoute()
        helpSupportRoute()
        privacyPolicyRoute()
        contactUsRoute()
        feedbackRoute()

        nestedNavHostRoute(appState = appState)
    }
}