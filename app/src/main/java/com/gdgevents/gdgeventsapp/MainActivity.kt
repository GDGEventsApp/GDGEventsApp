package com.gdgevents.gdgeventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.gdgevents.gdgeventsapp.common.navigation.GdgNavHost
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.HomeScreen
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            GDGEventsAppTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    GdgNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )

                }
            }
        }
    }
}



                HomeScreen()
            }
        }
    }
}
