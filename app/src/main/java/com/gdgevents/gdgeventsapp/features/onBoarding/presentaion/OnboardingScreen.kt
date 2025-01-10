package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    event:( OnBoardingEvent) -> Unit,
    onItemClicked: () -> Unit,
)
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
                    event(OnBoardingEvent.SaveAppEntry)
                    onItemClicked()
                })
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
                            scope.launch {
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
                    .clickable {
                        event(OnBoardingEvent.SaveAppEntry)
                        onItemClicked()
                    }
            )
        }

    }
}



@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3A
)

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
        OnboardingScreen(
            event = {},
            onItemClicked = {}
        )
    }

}



