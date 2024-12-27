package com.gdgevents.gdgeventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.HomeRoute
import com.gdgevents.gdgeventsapp.common.navigation.OutNavHost
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.NestedNavRoute
import com.gdgevents.gdgeventsapp.common.navigation.OutRoute.OnboardingRoute
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isFirstLaunch = true // get user info from datastore
        val outStartDestination = if (isFirstLaunch) OnboardingRoute else NestedNavRoute
        val nestedStartDestination = HomeRoute

        installSplashScreen()
        enableEdgeToEdge()

        setContent {

            val appState = rememberGdgAppState(
                outStartDestination = outStartDestination,
                nestedStartDestination = nestedStartDestination,
            )

            GDGEventsAppTheme {
                OutNavHost(
                    modifier = Modifier.fillMaxSize(),
                    appState = appState
                )
            }
        }
    }
}
