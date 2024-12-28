package com.gdgevents.gdgeventsapp.features.onBoarding.model

import androidx.annotation.DrawableRes
import com.gdgevents.gdgeventsapp.R

data class OnBoardModel(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)

val onBoardList = listOf(
    OnBoardModel(
        id = 0,
        title = "To Look Up More Events or Activities Nearby By Map",
        description = " In publishing and graphic design, Lorem is a placeholder text commonly.",
        imageRes = R.drawable.onboarding_pic_1
    ),
    OnBoardModel(
        id = 1,
        title = "Explore More About Event Technologies",
        description = " In publishing and graphic design, Lorem is a placeholder text commonly",
        imageRes = R.drawable.onboarding_pic_2
    ),
)