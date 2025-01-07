package com.gdgevents.gdgeventsapp.features.onBoarding.components


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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.onPrimary,
    unSelectedColor: Color = MaterialTheme.colorScheme.secondary,
    dotHeight: Dp = 8.dp,
    normalDotWidth: Dp = 8.dp,
    selectedDotWidth: Dp = 33.dp,
    spacing: Dp = 4.dp
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .height(dotHeight),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            val width by animateDpAsState(
                targetValue = if (index == selectedIndex) selectedDotWidth else normalDotWidth,
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
                    .height(dotHeight)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}