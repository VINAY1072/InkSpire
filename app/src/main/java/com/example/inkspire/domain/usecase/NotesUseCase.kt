package com.example.inkspire.domain.usecase

data class NotesUseCase(
    val insertUseCase: InsertUseCase,
    val getNotesUseCase: GetNotesUseCase,
    val getNoteByIdUseCase: GetNoteByIdUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)