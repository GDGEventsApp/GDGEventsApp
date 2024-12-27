package com.gdgevents.gdgeventsapp.features.settings.presentaion.privacyPolicy

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.PrivacyPolicyRoute

fun NavController.navigateToPrivacyPolicyScreen(navOptions: NavOptions? = null) =
    navigate(PrivacyPolicyRoute, navOptions)

fun NavGraphBuilder.privacyPolicyRoute() {
    composable<PrivacyPolicyRoute> {
        PrivacyPolicyScreen()
    }
}