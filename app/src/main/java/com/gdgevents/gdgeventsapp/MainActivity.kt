package com.gdgevents.gdgeventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gdgevents.gdgeventsapp.features.map.presentaion.MapScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute.HomeRoute
import com.gdgevents.gdgeventsapp.common.navigation.MainNavHost
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.MapRoute
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.OnboardingRoute
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.OnBoardingViewModel
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            val hasEntered by viewModel.hasEntered.collectAsStateWithLifecycle()

            val mainStartDestination = if (!hasEntered) OnboardingRoute else MapRoute
            val bottomBarStartDestination = HomeRoute
            val appState = rememberGdgAppState(
                mainStartDestination = mainStartDestination,
                bottomBarStartDestination = bottomBarStartDestination,
            )

            GDGEventsAppTheme {
                MainNavHost(
                    modifier = Modifier.fillMaxSize(),
                    appState = appState
                )
            }
        }
    }
}
