package com.example.inkspire.domain.usecase

import com.example.inkspire.domain.models.Note
import com.example.inkspire.domain.models.SortOrder
import com.example.inkspire.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(order: SortOrder) : Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (order) {
                SortOrder.TITLE -> notes.sortedBy { it.title }
                SortOrder.CREATED_DATE_ASC -> notes.sortedBy { it.createdTime }
                SortOrder.CREATED_DATE_DESC -> notes.sortedByDescending { it.createdTime }
                SortOrder.MODIFIED_DATE_ASC -> notes.sortedBy { it.modifiedTime }
                SortOrder.MODIFIED_DATE_DESC -> notes.sortedByDescending { it.modifiedTime }
            }
        }
    }
}