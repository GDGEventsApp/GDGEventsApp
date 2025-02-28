package com.gdgevents.gdgeventsapp.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.ui.theme.AppShapes
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

@Composable
fun GdgButton(
    modifier: Modifier = Modifier,
    text: String,
    onButtonClick:() -> Unit
) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = AppShapes.medium)
            .fillMaxWidth()
            .height(50.dp)

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun GdgButtonPreview() {
    GDGEventsAppTheme {
        GdgButton(
            onButtonClick = {},
            text = "Get started"
        )
    }
}