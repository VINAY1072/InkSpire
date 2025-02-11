package com.example.inkspire.domain.models

data class CreateUiState(
    val title: String = "",
    val description: String = "",
    val shouldGoBack: Boolean = false,
    val shouldAllowSave: Boolean = false
)