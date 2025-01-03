package com.gdgevents.gdgeventsapp.features.user.presentaion.feedback

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.FeedbackRoute

fun NavController.navigateToFeedbackScreen(navOptions: NavOptions? = null) =
    navigate(FeedbackRoute, navOptions)

fun NavGraphBuilder.feedbackRoute() {
    composable<FeedbackRoute> {
        FeedbackScreen()
    }
}