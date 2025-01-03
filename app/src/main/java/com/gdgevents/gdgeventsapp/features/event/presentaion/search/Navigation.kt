package com.gdgevents.gdgeventsapp.features.event.presentaion.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.SearchRoute

fun NavController.navigateToSearchScreen(navOptions: NavOptions? = null) =
    navigate(SearchRoute, navOptions)

fun NavGraphBuilder.searchRoute(appState: GdgAppState) {
    composable<SearchRoute> {
        SearchScreen(appState = appState)
    }
}