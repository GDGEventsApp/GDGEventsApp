package com.gdgevents.gdgeventsapp.features.event.presentaion.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.DetailRoute

fun NavController.navigateToDetail(navOptions: NavOptions? = null) =
    navigate(DetailRoute, navOptions)

fun NavGraphBuilder.detailRoute(appState: GdgAppState) {
    composable<DetailRoute> {
        DetailsScreen(appState)
    }
}