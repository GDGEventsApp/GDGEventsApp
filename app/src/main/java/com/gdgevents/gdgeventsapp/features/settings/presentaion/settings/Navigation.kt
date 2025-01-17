package com.gdgevents.gdgeventsapp.features.settings.presentaion.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute.SettingsRoute

fun NavController.navigateToSettingsScreen(navOptions: NavOptions? = null) =
    navigate(SettingsRoute, navOptions)

fun NavGraphBuilder.settingsRoute(appState: GdgAppState) {
    composable<SettingsRoute> {
        SettingsScreen(appState = appState)
    }
}