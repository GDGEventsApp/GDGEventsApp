package com.gdgevents.gdgeventsapp.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.addHomeRoute

@Composable
fun GdgNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    // implement NavHost
    NavHost(
        navController = navController,
        startDestination = Destination.HomeScreen.route,
    ) {
        // add NavGraph
        addHomeRoute(navController)

    }
}