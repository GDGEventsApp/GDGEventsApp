package com.gdgevents.gdgeventsapp

data class TopLevelRoute(
    val route: AppRoute,
    val selectedIcon: Int,
    val unselectedIcon: Int
)


val topLevelRoutes = listOf(
    TopLevelRoute(AppRoute.Home, R.drawable.ic_home_selected, R.drawable.ic_home_unselected),
    TopLevelRoute(AppRoute.MyAgenda, R.drawable.ic_agenda_selected, R.drawable.ic_agenda_unselected),
    TopLevelRoute(AppRoute.Favorite, R.drawable.ic_favorite_selected, R.drawable.ic_favorite_unselected),
    TopLevelRoute(AppRoute.Settings, R.drawable.ic_settings_selected, R.drawable.ic_settings_unselected)
)

