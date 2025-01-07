package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdgevents.gdgeventsapp.features.onBoarding.components.OnBoardItem

import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Skip",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 45.dp, end = 24.dp)
                .clickable {
                    //TODO: Navigate to another screen ( GET STARTED)
                },
        )
            OnBoardItem()

    }
}




@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3A
)
@Composable
private fun OnBoardingScreenPrev() {
    GDGEventsAppTheme {
        OnBoardingScreen()
    }
}