package com.gdgevents.gdgeventsapp.features.event.presentaion.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.features.event.domain.Event

@Composable
fun EventsRow(
    events: List<Event>,
    onClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.height(220.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(events, key = { it.id }) { event ->
            EventCard(
                event = event,
                onClick = onClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}
