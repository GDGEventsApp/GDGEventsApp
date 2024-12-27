package com.gdgevents.gdgeventsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.FavoriteRoute
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.HomeRoute
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.MyAgendaRoute
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.SettingsRoute
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute
import com.gdgevents.gdgeventsapp.common.navigation.navigateToNestedNavHost
import com.gdgevents.gdgeventsapp.features.event.presentaion.details.navigateToDetail
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.navigateToHome
import com.gdgevents.gdgeventsapp.features.event.presentaion.search.navigateToSearchScreen
import com.gdgevents.gdgeventsapp.features.map.presentaion.navigateToMapScreen
import com.gdgevents.gdgeventsapp.features.settings.presentaion.about.navigateToAboutScreen
import com.gdgevents.gdgeventsapp.features.settings.presentaion.helpSupport.navigateToHelpSupportScreen
import com.gdgevents.gdgeventsapp.features.settings.presentaion.privacyPolicy.navigateToPrivacyPolicyScreen
import com.gdgevents.gdgeventsapp.features.settings.presentaion.settings.navigateToSettingsScreen
import com.gdgevents.gdgeventsapp.features.user.presentaion.contactUs.navigateToContactUsScreen
import com.gdgevents.gdgeventsapp.features.user.presentaion.favorites.navigateToFavoriteScreen
import com.gdgevents.gdgeventsapp.features.user.presentaion.feedback.navigateToFeedbackScreen
import com.gdgevents.gdgeventsapp.features.user.presentaion.myAgenda.navigateToMyAgendaScreen

@Composable
fun rememberGdgAppState(
    outStartDestination: OutRoute,
    nestedStartDestination: NestedRoute,
    outNavController: NavHostController = rememberNavController(),
    nestedNavController: NavHostController = rememberNavController(),
): GdgAppState {
    return remember(
        outStartDestination,
        nestedStartDestination,
        outNavController,
        nestedNavController,
    ) {
        GdgAppState(
            outStartDestination = outStartDestination,
            nestedStartDestination = nestedStartDestination,
            outNavController = outNavController,
            nestedNavController = nestedNavController
        )
    }
}

class GdgAppState(
    val outStartDestination: OutRoute,
    val nestedStartDestination: NestedRoute,
    val outNavController: NavHostController,
    val nestedNavController: NavHostController,
) {
    val nestedDestination: NavDestination?
        @Composable get() = nestedNavController
            .currentBackStackEntryAsState().value?.destination

    val canOutNavigateUp: Boolean
        @Composable get() = outNavController.previousBackStackEntry != null

    fun goToMapScreen() = outNavController.navigateToMapScreen()
    fun goToSearchScreen() = outNavController.navigateToSearchScreen()
    fun goToAboutScreen() = outNavController.navigateToAboutScreen()
    fun goToHelpSupportScreen() = outNavController.navigateToHelpSupportScreen()
    fun goToDetail() = outNavController.navigateToDetail()
    fun goToPrivacyPolicyScreen() = outNavController.navigateToPrivacyPolicyScreen()
    fun goToContactUsScreen() = outNavController.navigateToContactUsScreen()
    fun goToFeedbackScreen() = outNavController.navigateToFeedbackScreen()
    fun outNavigateUp() = outNavController.navigateUp()

    fun goToNestedNavHost() = outNavController.navigateToNestedNavHost(
        navOptions {
            popUpTo(outNavController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    )

    fun navigateToNestedDestination(route: NestedRoute) {
        val navOptions = navOptions {
            popUpTo(nestedNavController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (route) {
            HomeRoute -> nestedNavController.navigateToHome(navOptions)
            MyAgendaRoute -> nestedNavController.navigateToMyAgendaScreen(navOptions)
            FavoriteRoute -> nestedNavController.navigateToFavoriteScreen(navOptions)
            SettingsRoute -> nestedNavController.navigateToSettingsScreen(navOptions)
        }
    }
}