package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgevents.gdgeventsapp.common.navigation.Destination
import com.gdgevents.gdgeventsapp.core.datastore.domain.usecases.AppEntryUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel@Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _startDestination: MutableState<String> = mutableStateOf(Destination.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect() { completed ->
                if (completed) {
                    _startDestination.value = Destination.Home.route
                } else {
                    _startDestination.value = Destination.OnBoarding.route
                }
            }
        }
    }
}