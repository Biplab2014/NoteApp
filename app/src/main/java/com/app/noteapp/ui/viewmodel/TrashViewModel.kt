package com.app.noteapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.noteapp.data.model.Note
import com.app.noteapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrashViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    
    private val _deletedNotes = MutableStateFlow<List<Note>>(emptyList())
    val deletedNotes: StateFlow<List<Note>> = _deletedNotes.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    fun loadDeletedNotes() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getDeletedNotes().collect { notes ->
                _deletedNotes.value = notes
                _isLoading.value = false
            }
        }
    }
    
    fun restoreNote(noteId: String) {
        viewModelScope.launch {
            repository.restoreFromTrash(noteId)
        }
    }
    
    fun permanentlyDeleteNote(noteId: String) {
        viewModelScope.launch {
            repository.permanentlyDeleteNote(noteId)
        }
    }
    
    fun emptyTrash() {
        viewModelScope.launch {
            _deletedNotes.value.forEach { note ->
                repository.permanentlyDeleteNote(note.id)
            }
        }
    }
}
