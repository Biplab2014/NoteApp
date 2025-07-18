package com.app.noteapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.noteapp.data.model.Note
import com.app.noteapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _selectedColorFilter = MutableStateFlow<String?>(null)
    val selectedColorFilter: StateFlow<String?> = _selectedColorFilter.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    val notes = combine(
        repository.getAllNotes(),
        _searchQuery,
        _selectedColorFilter
    ) { allNotes, query, colorFilter ->
        var filteredNotes = allNotes
        
        if (query.isNotBlank()) {
            filteredNotes = filteredNotes.filter { note ->
                note.title.contains(query, ignoreCase = true) ||
                note.content.contains(query, ignoreCase = true)
            }
        }
        
        if (colorFilter != null) {
            filteredNotes = filteredNotes.filter { note ->
                note.colorTag == colorFilter
            }
        }
        
        filteredNotes
    }
    
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
    
    fun updateColorFilter(colorTag: String?) {
        _selectedColorFilter.value = colorTag
    }
    
    fun createNote(title: String = "", content: String = ""): String {
        val noteId = UUID.randomUUID().toString()
        val currentTime = Date()
        
        val note = Note(
            id = noteId,
            title = title,
            content = content,
            createdAt = currentTime,
            updatedAt = currentTime
        )
        
        viewModelScope.launch {
            repository.insertNote(note)
        }
        
        return noteId
    }
    
    fun togglePinStatus(noteId: String, isPinned: Boolean) {
        viewModelScope.launch {
            repository.updatePinStatus(noteId, isPinned)
        }
    }
    
    fun moveNoteToTrash(noteId: String) {
        viewModelScope.launch {
            repository.moveToTrash(noteId)
        }
    }
    
    fun restoreNoteFromTrash(noteId: String) {
        viewModelScope.launch {
            repository.restoreFromTrash(noteId)
        }
    }
    
    fun permanentlyDeleteNote(noteId: String) {
        viewModelScope.launch {
            repository.permanentlyDeleteNote(noteId)
        }
    }
    
    fun cleanupOldTrashedNotes() {
        viewModelScope.launch {
            repository.deleteOldTrashedNotes()
        }
    }
}
