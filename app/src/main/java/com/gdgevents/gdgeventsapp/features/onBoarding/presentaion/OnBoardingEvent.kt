package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

sealed class OnBoardingEvent {
    data object SaveAppEntry : OnBoardingEvent()
}