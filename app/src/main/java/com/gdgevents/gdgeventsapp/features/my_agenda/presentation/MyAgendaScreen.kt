package com.gdgevents.gdgeventsapp.features.my_agenda.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.gdgevents.gdgeventsapp.common.navigation.Screen

@Composable
fun MyAgendaScreen() {
    MyAgendaContent()
}

@Composable
fun MyAgendaContent() {
    // Your Search Screen UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            , Arrangement.Center, Alignment.CenterHorizontally
    ) {
        Text(text = "My Agenda Screen")

    }
}