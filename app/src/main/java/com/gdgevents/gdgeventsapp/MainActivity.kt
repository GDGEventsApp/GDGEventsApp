package com.gdgevents.gdgeventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gdgevents.gdgeventsapp.common.navigation.BottomNavRoute.HomeRoute
import com.gdgevents.gdgeventsapp.common.navigation.MainNavHost
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.NestedNavRoute
import com.gdgevents.gdgeventsapp.common.navigation.MainRoute.OnboardingRoute
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isFirstLaunch = true // get user info from datastore
        val mainStartDestination = if (isFirstLaunch) OnboardingRoute else NestedNavRoute
        val bottomBarStartDestination = HomeRoute

        installSplashScreen()
        enableEdgeToEdge()

        setContent {

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
