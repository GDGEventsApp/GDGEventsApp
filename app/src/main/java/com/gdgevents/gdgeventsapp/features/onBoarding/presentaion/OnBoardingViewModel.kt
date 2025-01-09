package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgevents.gdgeventsapp.core.datastore.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    // add implementation
    fun saveOnBoardingState() {
        viewModelScope.launch(Dispatchers.IO) {
            appEntryUseCases.saveAppEntry()
        }
    }

}