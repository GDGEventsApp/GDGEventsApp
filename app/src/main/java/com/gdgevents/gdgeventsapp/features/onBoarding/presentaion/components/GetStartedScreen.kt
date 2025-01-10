package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import com.gdgevents.gdgeventsapp.common.components.GdgButton
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.R
import ir.kaaveh.sdpcompose.sdp

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
                append(stringResource(R.string.welcome_to))
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append(stringResource(R.string.gdg_events))
                }
                append(stringResource(R.string.app))
            },
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.discover_gdg_events_around_you),
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
@Preview()
@Composable
private fun GetStartedScreenPreview() {
    GDGEventsAppTheme {
        GetStartedScreen(onClick = {})
    }
}