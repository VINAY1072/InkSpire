package com.example.inkspire

sealed class Screen(val route: String) {
    data object Home: Screen(HOME)
    data object Create: Screen("$CREATE/{id}")

    companion object {
        const val HOME = "notes_screen"
        const val CREATE = "add_edit_note_screen"
    }
}