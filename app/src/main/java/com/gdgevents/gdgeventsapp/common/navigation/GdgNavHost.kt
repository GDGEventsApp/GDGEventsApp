package com.gdgevents.gdgeventsapp.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdgevents.gdgeventsapp.features.map.presentaion.MapScreen
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.OnBoardingEvent
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.OnBoardingViewModel
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.OnboardingScreen

@Composable
fun GdgNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel : OnBoardingViewModel = hiltViewModel()
    val hasEntered = viewModel.hasEntered.collectAsState(initial = false)
    NavHost(
        navController = navController,
        startDestination = if(hasEntered.value) Destination.Map.route else Destination.OnBoarding.route,
        ){
        composable(route=Destination.OnBoarding.route) {
            OnboardingScreen(
                event = viewModel::onEvent,
                onItemClicked = {
                    viewModel.onEvent(OnBoardingEvent.SaveAppEntry)
                    navController.navigate(Destination.Map.route){
                        popUpTo(Destination.OnBoarding.route){
                            inclusive=true
                        }
                    }
                }
            )
             }

        composable(route=Destination.Map.route) { MapScreen()  }

    }

}