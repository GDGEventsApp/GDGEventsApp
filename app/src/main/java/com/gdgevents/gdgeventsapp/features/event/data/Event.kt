package com.gdgevents.gdgeventsapp.features.event.data

import com.gdgevents.gdgeventsapp.R

data class Event(
    val image: Int,
    val title: String,
    val location: String,
    val date : String
) {

    companion object {
        fun dummyData(): List<Event> {
            return listOf(
                Event(
                    image = R.drawable.ai_image,
                    title = "Gemma 2 Ai Challenge",
                    location = "Location",
                    date = "28 Nov 2024 - 1 Dec 2024"
                ),
                Event(
                    image = R.drawable.ai_image,
                    title = "Gemma 2 Ai Challenge",
                    location = "Location",
                    date = "28 Nov 2024 - 1 Dec 2024"
                ),
                Event(
                    image = R.drawable.ai_image,
                    title = "Gemma 2 Ai Challenge",
                    location = "Location",
                    date = "28 Nov 2024 - 1 Dec 2024"
                ),
                Event(
                    image = R.drawable.ai_image,
                    title = "Gemma 2 Ai Challenge",
                    location = "Location",
                    date = "28 Nov 2024 - 1 Dec 2024"
                )
            )

        }
    }
}