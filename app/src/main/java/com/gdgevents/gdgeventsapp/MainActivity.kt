package com.gdgevents.gdgeventsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.gdgevents.gdgeventsapp.common.navigation.GdgNavHost
import com.gdgevents.gdgeventsapp.core.datastore.data.UserPreferencesRepoImpl
import com.gdgevents.gdgeventsapp.core.datastore.domain.usecases.AppEntryUseCases
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.OnBoardingScreen
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.SplashViewModel
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GDGEventsAppTheme {
                val screen by splashViewModel.startDestination
                val navController= rememberNavController()

                //OnBoardingScreen(navController = navController)
                GdgNavHost(navController = navController, startDestination = screen)
            }
        }
    }
}


