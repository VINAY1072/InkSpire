package com.example.inkspire.domain.models

data class CreateUiState(
    val title: String = "",
    val description: String = "",
    val id: Int? = null,
    val shouldAllowSave: Boolean = false
)