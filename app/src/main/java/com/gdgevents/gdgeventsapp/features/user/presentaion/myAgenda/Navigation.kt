package com.gdgevents.gdgeventsapp.features.user.presentaion.myAgenda

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.MyAgendaRoute

fun NavController.navigateToMyAgendaScreen(navOptions: NavOptions? = null) =
    navigate(MyAgendaRoute, navOptions)

fun NavGraphBuilder.myAgendaRoute(appState: GdgAppState) {
    composable<MyAgendaRoute> {
        MyAgendaScreen(appState = appState)
    }
}