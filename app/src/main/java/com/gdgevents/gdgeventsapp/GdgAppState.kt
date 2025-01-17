package com.gdgevents.gdgeventsapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute.FavoriteRoute
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute.HomeRoute
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute.MyAgendaRoute
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute.SettingsRoute
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute
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
    mainStartDestination: MainRoute,
    bottomBarStartDestination: BottomNavRoute,
    mainNavController: NavHostController = rememberNavController(),
    bottomBarNavController: NavHostController = rememberNavController(),
): GdgAppState {
    return remember(
        mainStartDestination,
        bottomBarStartDestination,
        mainNavController,
        bottomBarNavController,
    ) {
        GdgAppState(
            mainStartDestination = mainStartDestination,
            bottomBarStartDestination = bottomBarStartDestination,
            mainNavController = mainNavController,
            bottomBarNavController = bottomBarNavController
        )
    }
}

class GdgAppState(
    val mainStartDestination: MainRoute,
    val bottomBarStartDestination: BottomNavRoute,
    val mainNavController: NavHostController,
    val bottomBarNavController: NavHostController,
) {
    val bottomBarDestination: NavDestination?
        @Composable get() = bottomBarNavController
            .currentBackStackEntryAsState().value?.destination

    val canMainNavigateUp: Boolean
        @Composable get() = mainNavController.previousBackStackEntry != null

    fun goToMapScreen() = mainNavController.navigateToMapScreen()
    fun goToSearchScreen() = mainNavController.navigateToSearchScreen()
    fun goToAboutScreen() = mainNavController.navigateToAboutScreen()
    fun goToHelpSupportScreen() = mainNavController.navigateToHelpSupportScreen()
    fun goToDetail() = mainNavController.navigateToDetail()
    fun goToPrivacyPolicyScreen() = mainNavController.navigateToPrivacyPolicyScreen()
    fun goToContactUsScreen() = mainNavController.navigateToContactUsScreen()
    fun goToFeedbackScreen() = mainNavController.navigateToFeedbackScreen()
    fun mainNavigateUp() = mainNavController.navigateUp()

    fun goToNestedNavHost() = mainNavController.navigateToNestedNavHost(
        navOptions {
            popUpTo(mainNavController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    )

    fun navigateToBottomNavDestination(route: BottomNavRoute) {
        val navOptions = navOptions {
            popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (route) {
            HomeRoute -> bottomBarNavController.navigateToHome(navOptions)
            MyAgendaRoute -> bottomBarNavController.navigateToMyAgendaScreen(navOptions)
            FavoriteRoute -> bottomBarNavController.navigateToFavoriteScreen(navOptions)
            SettingsRoute -> bottomBarNavController.navigateToSettingsScreen(navOptions)
        }
    }
}