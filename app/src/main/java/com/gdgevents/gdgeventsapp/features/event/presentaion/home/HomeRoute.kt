package com.gdgevents.gdgeventsapp.features.event.presentaion.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.Destination


fun NavGraphBuilder.addHomeRoute(navController: NavController) {
    composable(Destination.HomeScreen.route) {
        HomeScreen(navController = navController)
    }
}