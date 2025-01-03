package com.gdgevents.gdgeventsapp.features.settings.presentaion.helpSupport

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.HelpSupportRoute

fun NavController.navigateToHelpSupportScreen(navOptions: NavOptions? = null) =
    navigate(HelpSupportRoute, navOptions)

fun NavGraphBuilder.helpSupportRoute() {
    composable<HelpSupportRoute> {
        HelpSupportScreen()
    }
}