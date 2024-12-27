package com.gdgevents.gdgeventsapp.features.event.presentaion.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) = navigate(HomeRoute, navOptions)

fun NavGraphBuilder.homeRoute(appState: GdgAppState) {
    composable<HomeRoute> {
        HomeScreen(appState)
    }
}