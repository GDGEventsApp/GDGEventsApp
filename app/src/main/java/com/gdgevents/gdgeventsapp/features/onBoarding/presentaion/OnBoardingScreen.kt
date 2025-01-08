package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gdgevents.gdgeventsapp.common.navigation.Destination
import com.gdgevents.gdgeventsapp.features.onBoarding.components.OnBoardItem

import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    navController: NavHostController
) {
    OnBoardItem(onItemClicked = {
        //Log.d("OnBoardingTAG","OnBoardingItemClicked")
        // TODO: Navigate to another screen ( confirm location)
        navController.popBackStack()
        navController.navigate(Destination.Home.route)

    })
}




@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3A
)
@Composable
private fun OnBoardingScreenPrev() {
    GDGEventsAppTheme {
        //OnBoardingScreen()
    }
}