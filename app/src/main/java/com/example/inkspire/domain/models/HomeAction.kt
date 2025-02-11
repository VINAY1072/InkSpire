package com.example.inkspire.domain.models

sealed interface HomeAction {
    data class SetMenuDialogStatus(val status: Boolean) : HomeAction
    data class DeleteNote(val note: Note) : HomeAction
    data class SetBottomSheetStatus(val status: Pair<Boolean, Note?>) : HomeAction
    data class SortData(val order: SortOrder) : HomeAction
}