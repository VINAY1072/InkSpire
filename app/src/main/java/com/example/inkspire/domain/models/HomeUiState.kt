package com.example.inkspire.domain.models

data class HomeUiState(
    val notes: List<Note>? = null,
    val showMenu: Boolean = false,
    val showBottomSheet: Pair<Boolean, Note?> = Pair(false, null),
    val sortOrder: SortOrder = SortOrder.MODIFIED_DATE_DESC
)