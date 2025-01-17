package com.gdgevents.gdgeventsapp.features.user.presentaion.contactUs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.ContactUsRoute

fun NavController.navigateToContactUsScreen(navOptions: NavOptions? = null) =
    navigate(ContactUsRoute, navOptions)

fun NavGraphBuilder.contactUsRoute() {
    composable<ContactUsRoute> {
        ContactUsScreen()
    }
}