package com.example.inkspire.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val description: String,
    val createdTime: Long,
    val modifiedTime: Long,
    @PrimaryKey val id: Int? = null
)