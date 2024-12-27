package com.gdgevents.gdgeventsapp.common.navigation

import kotlinx.serialization.Serializable

sealed interface OutRoute {

    @Serializable
    data object OnboardingRoute : OutRoute

    @Serializable
    data object MapRoute : OutRoute

    @Serializable
    data object NestedNavRoute : OutRoute

    @Serializable
    data object SearchRoute : OutRoute

    @Serializable
    data object AboutRoute : OutRoute

    @Serializable
    data object HelpSupportRoute : OutRoute

    @Serializable
    data object PrivacyPolicyRoute : OutRoute

    @Serializable
    data object ContactUsRoute : OutRoute

    @Serializable
    data object FeedbackRoute : OutRoute

    @Serializable
    data object DetailRoute : OutRoute
}
