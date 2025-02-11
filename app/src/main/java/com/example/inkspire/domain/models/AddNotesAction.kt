package com.example.inkspire.domain.models

import org.w3c.dom.Text

sealed interface AddNotesAction {
    data object AddNote : AddNotesAction
    data class Title(val text: String) : AddNotesAction
    data class Description(val text: String) : AddNotesAction
}