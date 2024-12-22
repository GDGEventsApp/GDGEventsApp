package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdgevents.gdgeventsapp.features.onBoarding.components.OnBoardItem
import com.gdgevents.gdgeventsapp.features.onBoarding.model.onBoardModel
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {
    val currentPage by viewModel.currentPage.collectAsState()
    val nextButton = { viewModel.onNextClick() }
    val skipButton = { viewModel.onSkipClick() }

    Box(modifier = Modifier.fillMaxSize()) {
        OnBoardItem(
            page = onBoardModel[currentPage],
            onNextClick = nextButton,
            onSkipClick = skipButton
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnBoardingScreenPrev() {
    GDGEventsAppTheme {
        OnBoardingScreen()
    }
}