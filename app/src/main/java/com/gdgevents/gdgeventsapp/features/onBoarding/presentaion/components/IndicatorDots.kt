package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion.components
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.onPrimary,
    unSelectedColor: Color = MaterialTheme.colorScheme.secondary,
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .height(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            val width by animateDpAsState(
                targetValue = if (index == selectedIndex) 33.dp else 8.dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                label = "dot_width_$index"
            )
            val color by animateColorAsState(
                targetValue = if (index == selectedIndex) selectedColor else unSelectedColor,
                animationSpec = tween(300),
                label = "dot_color_$index"
            )
            Spacer(
                modifier = Modifier
                    .width(width)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}
@Preview
@Composable
private fun DotsIndicatorPreview() {
    GDGEventsAppTheme {
        DotsIndicator(
            totalDots = 3,
            selectedIndex = 1,
        )
    }
}