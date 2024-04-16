package com.loc.todoapp.util

sealed class UIEvent {
    data object PopBackStack: UIEvent()
    data class Navigate(val route: String): UIEvent()
    data class ShowSnackbar(
        val message: String,
        val actionLabel: String? = null
    ): UIEvent()
}