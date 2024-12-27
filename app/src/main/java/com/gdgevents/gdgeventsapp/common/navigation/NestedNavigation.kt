package com.gdgevents.gdgeventsapp.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.NestedNavRoute

fun NavController.navigateToNestedNavHost(navOptions: NavOptions? = null) =
    navigate(NestedNavRoute, navOptions)

fun NavGraphBuilder.nestedNavHostRoute(appState: GdgAppState) {
    composable<NestedNavRoute> {
        NestedNavHost(appState = appState)
    }
}