package com.gdgevents.gdgeventsapp.common.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.HomeScreen
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.OnBoardingScreen

@Composable
fun GdgNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String) {
    // implement NavHost
    NavHost(
        navController = navController,
        startDestination = startDestination
        )
    {
        composable(route=Destination.OnBoarding.route) { OnBoardingScreen(navController = navController)  }

        composable(route=Destination.Home.route) { HomeScreen()  }

    }

}