package com.gdgevents.gdgeventsapp

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val selectedIcon: Int,
    val unselectedIcon: Int
)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", "home_route", selectedIcon = R.drawable.ic_home_selected, unselectedIcon =R.drawable.ic_home_unselected),
    TopLevelRoute("My agenda", "agenda_route", selectedIcon =R.drawable.ic_agenda_selected,unselectedIcon = R.drawable.ic_agenda_unselected),
    TopLevelRoute("Favorite", "favorite_route",  selectedIcon =R.drawable.ic_favorite_selected,unselectedIcon =R.drawable.ic_favorite_unselected),
    TopLevelRoute("Settings", "settings_route", selectedIcon = R.drawable.ic_settings_selected,unselectedIcon =R.drawable.ic_settings_unselected)
)
