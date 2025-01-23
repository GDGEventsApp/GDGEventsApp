package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgevents.gdgeventsapp.features.onBoarding.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    private val _hasEntered = MutableStateFlow(false)
    val hasEntered: StateFlow<Boolean> = _hasEntered

    init {
        checkIfUserHasEntered()
    }

    private fun checkIfUserHasEntered() {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect { hasEntered ->
                _hasEntered.update { hasEntered }
            }
        }
    }
    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch(IO) {
            appEntryUseCases.saveAppEntry()
        }
    }

}
