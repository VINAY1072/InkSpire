package com.example.inkspire.domain.models

data class HomeUiState(
    val notes: List<Note>? = null,
    val showMenu: Boolean = false,
    val sortOrder: SortOrder = SortOrder.MODIFIED_DATE_DESC
)