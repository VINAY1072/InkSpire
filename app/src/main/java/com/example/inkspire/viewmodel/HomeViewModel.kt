package com.example.inkspire.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inkspire.domain.models.HomeAction
import com.example.inkspire.domain.models.HomeUiState
import com.example.inkspire.domain.models.SortOrder
import com.example.inkspire.domain.usecase.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val notesUseCase: NotesUseCase
) : ViewModel() {

    private val _initUiStateValue = HomeUiState()

    private val _uiState = MutableStateFlow(_initUiStateValue)
    val uiState = _uiState.onStart {
        fetchNotes(_uiState.value.sortOrder)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _initUiStateValue
    )

    var openCreateScreen = MutableSharedFlow<Int>()

    private fun openCreateScreen(id: Int) {
        viewModelScope.launch {
            openCreateScreen.emit(id)
        }
    }

    private fun fetchNotes(order: SortOrder) {
        viewModelScope.launch {
            notesUseCase.getNotesUseCase(order).collectLatest { notes ->
                _uiState.update {
                    it.copy(notes = notes)
                }
            }
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.DeleteNote -> {
                _uiState.update {
                    it.copy(notes = it.notes?.filter { note -> note != action.note })
                }

                viewModelScope.launch {
                    notesUseCase.deleteNoteUseCase(action.note)
                }
            }

            is HomeAction.SetMenuDialogStatus -> {
                _uiState.update {
                    it.copy(showMenu = action.status)
                }
            }

            is HomeAction.CreateNote -> {
                openCreateScreen(action.id)
            }

            is HomeAction.SortData -> {
                _uiState.update {
                    it.copy(sortOrder = action.order)
                }
                fetchNotes(action.order)
            }
        }
    }

}