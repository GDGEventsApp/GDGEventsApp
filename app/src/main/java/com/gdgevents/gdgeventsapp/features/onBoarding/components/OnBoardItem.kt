package com.gdgevents.gdgeventsapp.features.onBoarding.components

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
import com.gdgevents.gdgeventsapp.features.onBoarding.model.OnBoardModel
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import com.gdgevents.gdgeventsapp.features.onBoarding.model.onBoardList

@Composable
fun OnBoardItem(
    page: OnBoardModel,
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {


    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .height(500.sdp)
                .fillMaxWidth()
                .padding( bottom = 90.sdp,start = 50.sdp, end = 50.sdp),
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            OnboardingTextContainer(
                title = page.title,
                description = page.description,
                currentPage = page.id,
                totalPages = 2,
                onNextClick = { onNextClick() },
                onSkipClick = { onSkipClick() },
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
    onSkipClick: () -> Unit,
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .weight(2f)
                        .align(Alignment.CenterVertically)
                        .padding(start = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(totalPages) { index ->
                        val color = if (index == currentPage)
                            MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.secondary
                        Spacer(modifier = modifier.width(4.dp))
                        Box(
                            modifier = modifier
                                .height(8.dp)
                                .width(if (index == currentPage) 33.dp else 8.dp)
                                .background(color, CircleShape)
                                .padding(horizontal = 4.dp),
                        )
                    }
                }
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
private fun OnBoardModelPrev() {
    GDGEventsAppTheme {
        OnBoardItem(onBoardList[0])
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
        OnBoardItem(onBoardList[1])
    }

}



