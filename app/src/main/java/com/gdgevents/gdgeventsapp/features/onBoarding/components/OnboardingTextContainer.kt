package com.gdgevents.gdgeventsapp.features.onBoarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

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
            .padding(top = 64.dp, bottom = 64.dp, start = 20.dp, end = 20.dp),
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
                    modifier = modifier.weight(2f)
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


@Preview
@Composable
private fun ContainerPrv() {
    GDGEventsAppTheme {
        OnboardingTextContainer(
            title = "To Look Up More Events or Activities Nearby By Map",
            description = " In publishing and graphic design, Lorem is a placeholder text commonly",
            currentPage = 0,
            totalPages = 3,
            onNextClick = {},
            onSkipClick = {}
        )
    }

}