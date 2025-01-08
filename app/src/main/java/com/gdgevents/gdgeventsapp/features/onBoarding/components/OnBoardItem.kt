package com.gdgevents.gdgeventsapp.features.onBoarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.common.components.GdgButton
import com.gdgevents.gdgeventsapp.features.onBoarding.model.onBoardList
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@Composable
fun OnBoardItem(modifier: Modifier = Modifier,onItemClicked: () -> Unit)
{
    val pagerState = rememberPagerState(pageCount = { onBoardList.size+1 })
    val scope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize())
    {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            if (index < onBoardList.size) {
                //Log.d("pagerState",pagerState.currentPage.toString())
                Image(
                    painter = painterResource(id = onBoardList[index].imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(500.sdp)
                        .fillMaxWidth()
                        .padding(bottom = 90.sdp, start = 50.sdp, end = 50.sdp),
                )

            } else {
                GetStartedScreen(onClick = onItemClicked)
            }

        }
        if (pagerState.currentPage < onBoardList.size) {
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                OnboardingTextContainer(
                    modifier=
                    if(pagerState.currentPage== onBoardList.lastIndex)
                                Modifier.alpha(alpha = 1-pagerState.currentPageOffsetFraction)
                    else Modifier,
                    title = stringResource(onBoardList[pagerState.currentPage].title),
                    description = stringResource(onBoardList[pagerState.currentPage].description),
                    currentPage = onBoardList[pagerState.currentPage].id,
                    totalPages = pagerState.pageCount,
                    onNextClick = {
                            scope.launch() {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)

                            }
                    },
                )
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.TopEnd),
            visible = pagerState.currentPage< onBoardList.size
        ) {
            Text(
                text = stringResource(R.string.skip),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 45.dp, end = 24.dp)
                    .clickable(onClick = onItemClicked)
            )
        }

    }
}

@Composable
fun OnboardingTextContainer(
    title: String,
    description: String,
    currentPage: Int,
    totalPages: Int,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
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
                modifier = modifier.padding(top = 8.dp, bottom = 20.dp),
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
                    text = "Next",
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
fun GetStartedScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.sdp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            buildAnnotatedString {
                append("Welcome to \n")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("GDG Events ")
                }
                append("app ")
            },
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Discover GDG events around you",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        )
        Image(
            painter = painterResource(id = R.drawable.onboarding_pic_3),
            contentDescription = null,
            modifier = modifier
                .width(397.dp)
                .height(356.dp)
                .padding(bottom = 24.dp, top = 16.dp),
        )
        GdgButton(
            text = stringResource(R.string.get_started),
            onButtonClick=onClick,
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3A
)
@Composable
private fun GetStartedScreenPreview() {
    GDGEventsAppTheme {
        GetStartedScreen(onClick = {})
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_6
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3A
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3A
)
@Composable
private fun OnBoardModelPrev1() {
    GDGEventsAppTheme {
        OnBoardItem(onItemClicked = {})
    }

}



