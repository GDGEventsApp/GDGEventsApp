package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.features.onBoarding.util.WindowType
import com.gdgevents.gdgeventsapp.features.onBoarding.util.rememberWindowSize
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import ir.kaaveh.sdpcompose.sdp

@Composable
fun OnboardingTextContainer(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    currentPage: Int,
    totalPages: Int,
    onNextClick: () -> Unit,

    ) {
    val windowSize = rememberWindowSize()
    when (windowSize.width) {
        WindowType.SMALL -> {
            SmallSizeTextContainer(
                title = title,
                description = description,
                totalPages = totalPages,
                currentPage = currentPage,
                onNextClick = onNextClick
            )
        }
        WindowType.MEDIUM -> {
            MediumSizeTextContainer(
                title = title,
                description = description,
                totalPages = totalPages,
                currentPage = currentPage,
                onNextClick = onNextClick
            )
        }

        WindowType.LARGE -> {
            MediumSizeTextContainer(
                title = title,
                description = description,
                totalPages = totalPages,
                currentPage = currentPage,
                onNextClick = onNextClick
            )
        }

    }

}

@Composable
private fun SmallSizeTextContainer(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    totalPages: Int,
    currentPage: Int,
    onNextClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(top = 50.sdp, bottom = 50.sdp, start = 26.dp, end = 26.dp),
        contentAlignment = Alignment.BottomEnd

    ) {
        Column {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier
                    .padding(top = 8.dp, bottom = 20.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(9.dp)
            ) {
                DotsIndicator(
                    totalDots = totalPages,
                    selectedIndex = currentPage,
                    modifier = modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp),
                )
                Text(
                    text = stringResource(R.string.next),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .clickable { onNextClick() }
                )
            }
        }
    }
}

@Composable
private fun MediumSizeTextContainer(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    totalPages: Int,
    currentPage: Int,
    onNextClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)
            )
            .padding(top = 50.sdp, bottom = 50.sdp, start = 26.dp, end = 26.dp),
        contentAlignment = Alignment.Center

    ) {
        Column {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentHeight(),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier
                    .padding(top = 8.dp, bottom = 20.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .padding(9.dp)
            ) {
                DotsIndicator(
                    totalDots = totalPages,
                    selectedIndex = currentPage,
                    modifier = modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp),
                )
                Text(
                    text = stringResource(R.string.next),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .clickable { onNextClick() }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun OnboardingTextContainerPreview() {
    GDGEventsAppTheme {
        OnboardingTextContainer(
            title = stringResource(R.string.onboarding_title_1st_page),
            description = stringResource(R.string.onboarding_description_1st_page),
            currentPage = 1,
            totalPages = 3,
            onNextClick = {}
        )
    }

}