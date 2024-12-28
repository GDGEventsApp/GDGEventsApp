package com.gdgevents.gdgeventsapp.features.event.presentaion.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.features.event.data.Event
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.composables.EventListSection
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.composables.FeaturedEventsSection
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.composables.TopBar

@Composable
fun HomeScreen(
    featuredEvents: List<Int>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp)
            ) {
                // Featured Events Section
                item {
                    FeaturedEventsSection(featuredEvents)
                }

                // Upcoming Events Section
                item {
                    EventListSection(
                        title = "Upcoming Events",
                        events = Event.dummyData(),
                        onSeeAllClick = { /* Handle See All Click */ },
                    )
                }

                // Past Events Section
                item {
                    EventListSection(
                        title = "Past Events",
                        events = Event.dummyData(),
                        onSeeAllClick = { /* Handle See All Click */ },
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GDGApp(modifier: Modifier = Modifier) {
    HomeScreen(
        featuredEvents = listOf(
            R.drawable.img_devfest,
            R.drawable.img_devfest,
            R.drawable.img_devfest
        ),
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.background
        )
    )
}