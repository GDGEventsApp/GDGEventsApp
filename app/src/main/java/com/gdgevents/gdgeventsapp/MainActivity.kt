package com.gdgevents.gdgeventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.OnBoardingScreen
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GDGEventsAppTheme {
                OnBoardingScreen()
            }
        }
    }
}


