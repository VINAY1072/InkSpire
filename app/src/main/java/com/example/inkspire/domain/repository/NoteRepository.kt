package com.example.inkspire.domain.repository

import com.example.inkspire.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insert(note: Note)

    suspend fun delete(note: Note)
}