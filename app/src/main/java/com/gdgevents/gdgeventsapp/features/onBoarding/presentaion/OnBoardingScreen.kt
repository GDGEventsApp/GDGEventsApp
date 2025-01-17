package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdgevents.gdgeventsapp.GdgAppState

@Composable
fun OnBoardingScreen(
    appState: GdgAppState,
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("On Boarding Screen")
            Button(
                onClick = appState::goToMapScreen
            ) {
                Text("go to map screen")
            }
        }
    }
}