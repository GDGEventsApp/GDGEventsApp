package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = "Home")
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview(){
    HomeScreen()
}