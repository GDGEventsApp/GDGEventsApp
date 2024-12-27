package com.gdgevents.gdgeventsapp.features.event.presentaion.home

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
fun HomeScreen(
    appState: GdgAppState,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Home Screen")
            Button(
                onClick = appState::goToSearchScreen
            ) {
                Text("go To Search Screen")
            }
            Button(
                onClick = appState::goToDetail
            ) {
                Text("go To Detail Screen")
            }
        }
    }
}