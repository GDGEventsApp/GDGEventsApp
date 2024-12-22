package com.gdgevents.gdgeventsapp.features.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.features.onBoarding.model.OnBoardModel
import com.gdgevents.gdgeventsapp.features.onBoarding.model.onBoardModel
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

@Composable
fun OnBoardItem(
    page: OnBoardModel,
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxSize()
            .padding(top = 20.dp),
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = modifier
                .height(570.dp)
                .width(360.dp)
                .padding(top = 80.dp)
                .align(Alignment.TopCenter),
        )
        Column(modifier = modifier.align(Alignment.BottomCenter)) {
            OnboardingTextContainer(
                title = page.title,
                description = page.description,
                currentPage = page.id,
                totalPages = 2,
                onNextClick = {onNextClick()},
                onSkipClick = {onSkipClick()},
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnBoardModelPrev() {
    GDGEventsAppTheme {
        OnBoardItem(onBoardModel[0])
    }

}