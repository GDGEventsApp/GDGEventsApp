package com.gdgevents.gdgeventsapp.features.onBoarding.presentaion

import androidx.lifecycle.ViewModel
import com.gdgevents.gdgeventsapp.features.onBoarding.model.onBoardList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage

    private val totalPages: Int = onBoardList.size // Total number of pages

    fun onNextClick() {
        if (_currentPage.value < totalPages - 1) {
            _currentPage.update { it + 1 }
        }
    }

    fun onSkipClick() {
        _currentPage.update { totalPages - 1 }
    }

//    fun onPageChanged(page: Int) {
//        _currentPage.value = page // Update the current page when the user swipes manually
//    }
}