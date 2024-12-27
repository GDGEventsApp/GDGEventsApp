package com.gdgevents.gdgeventsapp.features.settings.presentaion.about

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.AboutRoute

fun NavController.navigateToAboutScreen(navOptions: NavOptions? = null) =
    navigate(AboutRoute, navOptions)

fun NavGraphBuilder.aboutRoute() {
    composable<AboutRoute> {
        AboutScreen()
    }
}