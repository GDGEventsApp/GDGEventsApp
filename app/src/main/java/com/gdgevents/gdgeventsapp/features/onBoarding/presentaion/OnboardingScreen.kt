package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.components.GetStartedScreen
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.components.OnboardingTextContainer
import com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.model.onBoardList
import com.gdgevents.gdgeventsapp.features.onBoarding.util.WindowType
import com.gdgevents.gdgeventsapp.features.onBoarding.util.rememberWindowSize
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onItemClicked: () -> Unit,
) {
    Log.d("OnboardingScreen", "OnboardingScreen started")
    val pagerState = rememberPagerState(pageCount = { onBoardList.size + 1 }, initialPage = 0)
    val scope = rememberCoroutineScope()
    val windowSize = rememberWindowSize()
    LaunchedEffect(pagerState.currentPage) {
        Log.d("OnboardingScreen", "Current Page: ${pagerState.currentPage}")
    }
    when (windowSize.width) {
        // smaller than 600dp
        WindowType.SMALL -> SmallScreen(modifier, pagerState, onItemClicked, scope)
        // smaller than 840dp & bigger than 840dp
        WindowType.MEDIUM, WindowType.LARGE -> MediumScreen(modifier, pagerState, onItemClicked, scope)
    }

}

@Composable
private fun SmallScreen(
    modifier: Modifier,
    pagerState: PagerState,
    onItemClicked: () -> Unit,
    scope: CoroutineScope
) {
    Box(modifier = modifier.fillMaxSize())
    {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            if (index < onBoardList.size) {
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
                GetStartedScreen(onClick = {
                    onItemClicked()
                })
            }

        }
        if (pagerState.currentPage < onBoardList.size) {
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                OnboardingTextContainer(
                    modifier =
                    if (pagerState.currentPage == onBoardList.lastIndex)
                        Modifier.alpha(alpha = 1 - pagerState.currentPageOffsetFraction)
                    else Modifier,
                    title = stringResource(onBoardList[pagerState.currentPage].title),
                    description = stringResource(onBoardList[pagerState.currentPage].description),
                    currentPage = onBoardList[pagerState.currentPage].id,
                    totalPages = pagerState.pageCount,
                    onNextClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                )
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.TopEnd),
            visible = pagerState.currentPage < onBoardList.size
        ) {
            Text(
                text = stringResource(R.string.skip),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 45.dp, end = 24.dp)
                    .clickable {
                        onItemClicked()
                    }
            )
        }

    }
}

@Composable
private fun MediumScreen(
    modifier: Modifier,
    pagerState: PagerState,
    onItemClicked: () -> Unit,
    scope: CoroutineScope
) {
    Box(modifier = modifier.fillMaxSize()) {


        Box(
            modifier = modifier.fillMaxSize()
        )
        {
            HorizontalPager(
                state = pagerState,

                ) { index ->
                if (index < onBoardList.size) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = onBoardList[index].imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .padding(16.sdp),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }


                } else {
                    GetStartedScreen(
                        onClick = {
                            onItemClicked()
                        })
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                if (pagerState.currentPage < onBoardList.size) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        OnboardingTextContainer(
                            modifier =
                            if (pagerState.currentPage == onBoardList.lastIndex)
                                Modifier.alpha(alpha = 1 - pagerState.currentPageOffsetFraction)
                            else Modifier,
                            title = stringResource(onBoardList[pagerState.currentPage].title),
                            description = stringResource(onBoardList[pagerState.currentPage].description),
                            currentPage = onBoardList[pagerState.currentPage].id,
                            totalPages = pagerState.pageCount,
                            onNextClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                        )
                    }
                }
            }


        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.TopEnd),
            visible = pagerState.currentPage < onBoardList.size
        ) {
            Text(
                text = stringResource(R.string.skip),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(top = 45.dp, end = 24.dp)
                    .clickable {
                        onItemClicked()
                    },
            )
        }
    }

}


@Preview(showBackground = true, device = Devices.PIXEL_TABLET)
@Composable
private fun OnBoardModelPrev1() {
    GDGEventsAppTheme {
        OnboardingScreen(
            onItemClicked = {}
        )
    }

}



