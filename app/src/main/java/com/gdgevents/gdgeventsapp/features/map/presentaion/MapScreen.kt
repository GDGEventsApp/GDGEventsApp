package com.gdgevents.gdgeventsapp.features.map.presentaion

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
fun MapScreen(
    appState: GdgAppState,
    viewModel: MapViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Map Screen")
            Button(
                onClick = appState::goToNestedNavHost
            ) {
                Text("go To Nested Nav Host")
            }
        }
    }
}