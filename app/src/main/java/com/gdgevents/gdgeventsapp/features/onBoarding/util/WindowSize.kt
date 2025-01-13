package com.gdgevents.gdgeventsapp.features.onBoarding.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

enum class WindowType {
    SMALL,
    MEDIUM,
    LARGE
}

@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current

    return when {
        configuration.screenWidthDp < 600 -> {
            WindowSize(WindowType.SMALL, WindowType.SMALL)
        }

        configuration.screenWidthDp < 840 -> {
            WindowSize(WindowType.MEDIUM, WindowType.MEDIUM)
        }

        else -> {
            WindowSize(WindowType.LARGE, WindowType.LARGE)
        }

    }
}