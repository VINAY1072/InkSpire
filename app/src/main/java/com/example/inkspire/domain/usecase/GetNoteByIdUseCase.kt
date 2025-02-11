package com.example.inkspire.domain.usecase

import com.example.inkspire.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int) = repository.getNoteById(id)
}