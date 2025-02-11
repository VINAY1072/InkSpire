package com.example.inkspire.data.repository

import com.example.inkspire.data.datasource.NoteDao
import com.example.inkspire.domain.models.Note
import com.example.inkspire.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}