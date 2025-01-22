package com.gdgevents.gdgeventsapp.features.settings.presentaion.settings

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
fun SettingsScreen(
    appState: GdgAppState,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Settings Screen")
            Button(
                onClick = appState::goToAboutScreen
            ) {
                Text("go To About Screen")
            }
            Button(
                onClick = appState::goToHelpSupportScreen
            ) {
                Text("go To Help Support Screen")
            }
            Button(
                onClick = appState::goToPrivacyPolicyScreen
            ) {
                Text("go To Privacy Policy Screen")
            }
            Button(
                onClick = appState::goToContactUsScreen
            ) {
                Text("go To Contact Us Screen")
            }
            Button(
                onClick = appState::goToFeedbackScreen
            ) {
                Text("go To Feedback Screen")
            }
        }
    }
}