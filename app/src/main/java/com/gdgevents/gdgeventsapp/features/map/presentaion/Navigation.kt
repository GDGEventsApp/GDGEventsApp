package com.gdgevents.gdgeventsapp.features.map.presentaion

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.MapRoute

fun NavController.navigateToMapScreen(navOptions: NavOptions? = null) =
    navigate(MapRoute, navOptions)

fun NavGraphBuilder.mapRoute(appState: GdgAppState) {
    composable<MapRoute> {
        MapScreen(appState = appState)
    }
}