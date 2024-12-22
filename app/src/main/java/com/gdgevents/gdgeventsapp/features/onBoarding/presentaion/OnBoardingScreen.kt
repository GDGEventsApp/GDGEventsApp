package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OnBoardingScreen(
    getStarted: () -> Unit,
    nextButton: () -> Unit,
    skipButton: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {

}