package com.loc.todoapp.util

import androidx.compose.material3.SnackbarDuration

sealed class UIEvent {

    data object PopBackStack: UIEvent()
    data class Navigate(val route: String): UIEvent()
    data class ShowSnackbar(
        val message: String,
        val actionLabel: String? = null,
        val duration: SnackbarDuration? = SnackbarDuration.Indefinite
    ): UIEvent()
}