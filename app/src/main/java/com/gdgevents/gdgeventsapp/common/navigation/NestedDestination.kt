package com.gdgevents.gdgeventsapp.common.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.FavoriteRoute
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.HomeRoute
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.MyAgendaRoute
import com.gdgevents.gdgeventsapp.common.navigation.NestedRoute.SettingsRoute

enum class NestedDestination(
    val route: NestedRoute,
    @DrawableRes val selectedIconId: Int,
    @DrawableRes val unselectedIconId: Int,
    @StringRes val labelId: Int,
) {
    Home(
        route = HomeRoute,
        selectedIconId = R.drawable.ic_selected_home,
        unselectedIconId = R.drawable.ic_home,
        labelId = R.string.home_label,
    ),
    MyAgenda(
        route = MyAgendaRoute,
        selectedIconId = R.drawable.ic_selected_my_agenda,
        unselectedIconId = R.drawable.ic_my_agenda,
        labelId = R.string.my_agenda_label,
    ),
    Favorite(
        route = FavoriteRoute,
        selectedIconId = R.drawable.ic_selected_favorite,
        unselectedIconId = R.drawable.ic_favorite,
        labelId = R.string.favorite_label,
    ),
    Settings(
        route = SettingsRoute,
        selectedIconId = R.drawable.ic_selected_settings,
        unselectedIconId = R.drawable.ic_settings,
        labelId = R.string.settings_label,
    ),
}