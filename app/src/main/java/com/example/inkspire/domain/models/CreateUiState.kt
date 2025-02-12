package com.example.inkspire.domain.models

data class CreateUiState(
    val title: String = "",
    val description: String = "",
    val shouldAllowSave: Boolean = false
)