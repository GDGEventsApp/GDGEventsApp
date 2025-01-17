package com.gdgevents.gdgeventsapp.features.user.presentaion.myAgenda

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdgevents.gdgeventsapp.GdgAppState

@Composable
fun MyAgendaScreen(
    appState: GdgAppState,
    viewModel: MyAgendaViewModel = hiltViewModel(),
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("My Agenda Screen")
        }
    }
}