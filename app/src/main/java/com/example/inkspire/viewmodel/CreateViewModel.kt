package com.example.inkspire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inkspire.domain.models.AddNotesAction
import com.example.inkspire.domain.models.CreateUiState
import com.example.inkspire.domain.models.Note
import com.example.inkspire.domain.usecase.NotesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CreateViewModel.CreateViewModelFactory::class)
class CreateViewModel @AssistedInject constructor(
    private val notesUseCase: NotesUseCase,
    @Assisted val id: Int?
) : ViewModel() {

    private val _initCreateUiState = CreateUiState()

    private val _uiState = MutableStateFlow(_initCreateUiState)

    private var createdTime: Long = 0L
    private var initialTitle: String = ""
    private var initialDescription: String = ""

    val uiState = _uiState.onStart {
        fetchNoteById(id)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _initCreateUiState
    )

    private fun fetchNoteById(id: Int?) {
        viewModelScope.launch {
            id?.takeIf { it != -1 }?.let {
                val note = notesUseCase.getNoteByIdUseCase(it)
                note?.let { n ->
                    _uiState.update { state->
                        state.copy(
                            title = n.title,
                            description = n.description,
                        )
                    }
                    createdTime = n.createdTime
                    initialTitle = n.title
                    initialDescription = n.description
                }
            }
        }
    }

    fun onAction(action: AddNotesAction) {
        when (action) {
            is AddNotesAction.AddNote -> {
                val currentTime = System.currentTimeMillis()
                val createdTimeValue = if (id != null && id != -1) currentTime else createdTime
                val id = if (id != null && id != -1) id else null
                val note = Note(
                    title = _uiState.value.title,
                    description = _uiState.value.description,
                    createdTime = createdTimeValue,
                    modifiedTime = currentTime,
                    id = id
                )
                viewModelScope.launch {
                    notesUseCase.insertUseCase(note)
                }
                _uiState.update {
                    it.copy(shouldGoBack = true)
                }
            }

            is AddNotesAction.Description -> {
                _uiState.update {
                    val isEditingExistingNote = id != null && id != -1
                    val hasContent = action.text.isNotBlank() || _uiState.value.title.isNotBlank()
                    val isModified = if (isEditingExistingNote) {
                        action.text != initialDescription || _uiState.value.title != initialTitle
                    } else {
                        true
                    }

                    it.copy(
                        description = action.text,
                        shouldAllowSave = hasContent && isModified
                    )
                }

            }

            is AddNotesAction.Title -> {
                _uiState.update {
                    val isEditingExistingNote = id != null && id != -1
                    val hasContent = action.text.isNotBlank() || _uiState.value.description.isNotBlank()
                    val isModified = if (isEditingExistingNote) {
                        action.text != initialTitle || _uiState.value.description != initialDescription
                    } else {
                        true
                    }

                    it.copy(
                        title = action.text,
                        shouldAllowSave = hasContent && isModified
                    )
                }

            }
        }
    }

    @AssistedFactory
    interface CreateViewModelFactory {
        fun create(
            id: Int?,
        ): CreateViewModel
    }

}