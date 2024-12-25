package com.gdgevents.gdgeventsapp.features.event.presentaion.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdgevents.gdgeventsapp.features.event.data.Events
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.composables.EventListSection
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.composables.FeaturedEventsSection
import com.gdgevents.gdgeventsapp.features.event.presentaion.home.composables.TopBar
import com.gdgevents.gdgeventsapp.R

@Composable
fun HomeScreen(
    featuredEvents: List<Int>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        item {
            TopBar()
        }
        item {
            FeaturedEventsSection(featuredEvents)
        }
        item {
            EventListSection(
                title = "Upcoming Events",
                events = listOf(
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                ),
                onSeeAllClick = { /* Handle see all click */ })
        }
        item {
            EventListSection(
                title = "Past Events",
                events = listOf(
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                    Events(
                        image = R.drawable.ai_image,
                        title = "Gemma 2 Ai Challenge",
                        locationIcon = R.drawable.location,
                        location = "Location",
                        dateIcon = R.drawable.calender,
                        date = "28 Nov 2024 - 1 Dec 2024"
                    ),
                ),
                onSeeAllClick = { /* Handle see all click */ })
        }
    }
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