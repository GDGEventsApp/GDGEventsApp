package com.gdgevents.gdgeventsapp.features.onBoarding.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gdgevents.gdgeventsapp.R

data class OnBoardModel(
    val id: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

val onBoardList = listOf(
    OnBoardModel(
        id = 0,
        title = R.string.onboarding_title_1st_page,
        description = R.string.onboarding_description_1st_page,
        imageRes = R.drawable.onboarding_pic_1
    ),
    OnBoardModel(
        id = 1,
        title = R.string.onboarding_title_2nd_page,
        description = R.string.onboarding_description_2nd_page,
        imageRes = R.drawable.onboarding_pic_2
    ),
)