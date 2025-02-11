package com.example.inkspire.domain.usecase

import com.example.inkspire.domain.models.Note
import com.example.inkspire.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.delete(note)
    }
}