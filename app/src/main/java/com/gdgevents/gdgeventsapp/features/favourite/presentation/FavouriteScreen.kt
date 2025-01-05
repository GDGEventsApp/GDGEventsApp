package com.gdgevents.gdgeventsapp.features.favourite.presentation

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
fun FavoriteScreen() {
    SearchContent()
}

@Composable
fun SearchContent() {
    // Your Search Screen UI
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text = "Favorite Screen")

    }
}
