package com.gdgevents.gdgeventsapp.features.user.presentaion.favorites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.FavoriteRoute

fun NavController.navigateToFavoriteScreen(navOptions: NavOptions? = null) =
    navigate(FavoriteRoute, navOptions)

fun NavGraphBuilder.favoriteRoute(appState: GdgAppState) {
    composable<FavoriteRoute> {
        FavoriteScreen(appState = appState)
    }
}