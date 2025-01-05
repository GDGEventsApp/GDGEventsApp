package com.gdgevents.gdgeventsapp.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gdgevents.gdgeventsapp.features.favourite.presentation.FavoriteScreen
import com.gdgevents.gdgeventsapp.features.home.presentation.HomeScreen
import com.gdgevents.gdgeventsapp.features.my_agenda.presentation.MyAgendaContent
import com.gdgevents.gdgeventsapp.features.search.presentation.SearchScreen
import com.gdgevents.gdgeventsapp.features.settings.presentaion.settings.SettingsScreen

@Composable
fun ContentScreen(modifier: Modifier, selectedIndex:Int){
    when (selectedIndex) {
        0 -> HomeScreen()
        1 -> MyAgendaContent()
        2 -> FavoriteScreen()
        3 -> SearchScreen()
        4 -> SettingsScreen()
    }
}