package com.gdgevents.gdgeventsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.gdgevents.gdgeventsapp.common.navigation.GdgNavHost
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        enableEdgeToEdge()

        setContent {
            GDGEventsAppTheme {
                Scaffold {
                    val navController = rememberNavController()
                    GdgNavHost(navController = navController)

                }
            }
        }
    }
}


