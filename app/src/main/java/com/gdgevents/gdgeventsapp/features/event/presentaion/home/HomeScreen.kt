package com.gdgevents.gdgeventsapp.features.event.presentaion.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.components.EventsRow
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.components.FeaturedEventsRow
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.components.GdgTopBar
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.components.TitleRow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val events by viewModel.eventList.collectAsStateWithLifecycle()
    val featuredEvents by viewModel.featuredEventList.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            GdgTopBar(
                location = "Alexandria",
                onNotificationClick = {},
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            // Featured Events Section
            item {
                TitleRow(
                    title = "Featured Events",
                    onSeeAllClick = {},
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
            item {
                FeaturedEventsRow(
                    featuredEvents = featuredEvents,
                    onEventClick = {},
                )
            }
            item {
                HorizontalDivider()
            }
            // Upcoming Events Section
            item {
                TitleRow(
                    title = "Upcoming Events",
                    onSeeAllClick = {},
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
            item {
                EventsRow(
                    events = events,
                    onClick = {},
                    onFavoriteClick = {}
                )
            }
            item {
                HorizontalDivider()
            }
            // Past Events Section
            item {
                TitleRow(
                    title = "Past Events",
                    onSeeAllClick = {},
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
            item {
                EventsRow(
                    events = events,
                    onClick = {},
                    onFavoriteClick = {}
                )
            }
        }
    }
}
