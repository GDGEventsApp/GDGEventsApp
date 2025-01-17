package com.gdgevents.gdgeventsapp.features.event.presentaion.home.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gdgevents.gdgeventsapp.features.event.domain.Event
import kotlinx.coroutines.delay

@Composable
fun FeaturedEventsRow(
    featuredEvents: List<Event>,
    onEventClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        pageCount = { featuredEvents.size }
    )
    // Auto-scroll animation
    LaunchedEffect(featuredEvents) {
        var isForwardDirection = true
        while (featuredEvents.isNotEmpty()) {
            delay(3000)
            if (pagerState.currentPage == featuredEvents.lastIndex) {
                isForwardDirection = false
            } else if (pagerState.currentPage == 0) {
                isForwardDirection = true
            }
            if (isForwardDirection) {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    animationSpec = tween(durationMillis = 300)
                )
            } else {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage - 1,
                    animationSpec = tween(durationMillis = 300)
                )
            }
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .height(200.dp),
        snapPosition = SnapPosition.Center,
    ) { page ->
        PagerContent(
            imageUrl = featuredEvents[page].imageUrl,
            modifier = Modifier
                .clickable { onEventClick(featuredEvents[page].id) }
        )
    }
    // Custom pager indicator
    DotsIndicator(
        totalDots = featuredEvents.size,
        selectedIndex = pagerState.currentPage,
        modifier = Modifier.padding(top = 16.dp),
    )
}

@Composable
fun PagerContent(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = modifier
                .height(200.dp)
                .shadow(4.dp, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillHeight
        )
    }
}
