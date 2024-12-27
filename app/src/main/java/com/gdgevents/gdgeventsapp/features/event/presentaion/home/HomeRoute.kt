package com.gdgevents.gdgeventsapp.features.event.presentaion.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.Destination


fun NavGraphBuilder.homeRoute() {
    composable<Destination.HomeRoute> {
        HomeScreen()
    }
}