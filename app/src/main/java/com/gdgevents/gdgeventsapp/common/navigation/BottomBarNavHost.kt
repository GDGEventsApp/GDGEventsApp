package com.gdgevents.gdgeventsapp.common.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import com.gdgevents.gdgeventsapp.GdgAppState
import com.gdgevents.gdgeventsapp.common.components.GdgNavItem
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.homeRoute
import com.gdgevents.gdgeventsapp.features.settings.presentaion.settings.settingsRoute
import com.gdgevents.gdgeventsapp.features.user.presentaion.favorites.favoriteRoute
import com.gdgevents.gdgeventsapp.features.user.presentaion.myAgenda.myAgendaRoute

@Composable
fun BottomBarNavHost(
    appState: GdgAppState,
) {
    val currentNestedDestination = appState.bottomBarDestination

    Scaffold(
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .clip(RectangleShape)
                        .background(MaterialTheme.colorScheme.onTertiary),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomBarDestination.entries.forEach { destination ->
                        val selected =
                            currentNestedDestination?.hasRoute(destination.route::class) ?: false
                        val (iconId, tintColor) =
                            if (selected) destination.selectedIconId to MaterialTheme.colorScheme.primary
                            else destination.unselectedIconId to MaterialTheme.colorScheme.tertiary
                        GdgNavItem(
                            iconId = iconId,
                            labelId = destination.labelId,
                            tintColor = tintColor,
                            onClick = {
                                appState.navigateToBottomNavDestination(destination.route)
                            },
                        )
                    }
                }
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
                .consumeWindowInsets(WindowInsets.navigationBars),
            navController = appState.bottomBarNavController,
            startDestination = appState.bottomBarStartDestination,
            enterTransition = {
                fadeIn(tween(300))
            },
            exitTransition = {
                fadeOut(tween(400))
            },
            popEnterTransition = {
                fadeIn(tween(300))
            },
            popExitTransition = {
                fadeOut(tween(400))
            },
        ) {
            homeRoute(appState = appState)
            myAgendaRoute(appState = appState)
            favoriteRoute(appState = appState)
            settingsRoute(appState = appState)
        }
    }
}

