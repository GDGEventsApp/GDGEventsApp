package com.gdgevents.gdgeventsapp.common.navigation

import kotlinx.serialization.Serializable

sealed interface MainRoute {

    @Serializable
    data object OnboardingRoute : MainRoute

    @Serializable
    data object MapRoute : MainRoute

    @Serializable
    data object NestedNavRoute : MainRoute

    @Serializable
    data object SearchRoute : MainRoute

    @Serializable
    data object AboutRoute : MainRoute

    @Serializable
    data object HelpSupportRoute : MainRoute

    @Serializable
    data object PrivacyPolicyRoute : MainRoute

    @Serializable
    data object ContactUsRoute : MainRoute

    @Serializable
    data object FeedbackRoute : MainRoute

    @Serializable
    data object DetailRoute : MainRoute
}
