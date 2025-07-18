package com.app.noteapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.noteapp.data.model.Note
import com.app.noteapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NoteEditorViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    
    private val _note = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?> = _note.asStateFlow()
    
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()
    
    private val _content = MutableStateFlow("")
    val content: StateFlow<String> = _content.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()
    
    private var autoSaveJob: Job? = null
    private var currentNoteId: String? = null
    
    fun loadNote(noteId: String?) {
        viewModelScope.launch {
            _isLoading.value = true
            
            if (noteId != null) {
                currentNoteId = noteId
                val loadedNote = repository.getNoteById(noteId)
                if (loadedNote != null) {
                    _note.value = loadedNote
                    _title.value = loadedNote.title
                    _content.value = loadedNote.content
                }
            } else {
                // Create new note
                currentNoteId = UUID.randomUUID().toString()
                val newNote = Note(
                    id = currentNoteId!!,
                    title = "",
                    content = "",
                    createdAt = Date(),
                    updatedAt = Date()
                )
                _note.value = newNote
                _title.value = ""
                _content.value = ""
            }
            
            _isLoading.value = false
        }
    }
    
    fun updateTitle(newTitle: String) {
        _title.value = newTitle
        scheduleAutoSave()
    }
    
    fun updateContent(newContent: String) {
        _content.value = newContent
        scheduleAutoSave()
    }
    
    fun updateColorTag(colorTag: String) {
        val currentNote = _note.value ?: return
        val updatedNote = currentNote.copy(
            colorTag = colorTag,
            updatedAt = Date()
        )
        _note.value = updatedNote
        saveNote()
    }
    
    private fun scheduleAutoSave() {
        autoSaveJob?.cancel()
        autoSaveJob = viewModelScope.launch {
            delay(3000) // Auto-save after 3 seconds of inactivity
            saveNote()
        }
    }
    
    fun saveNote() {
        val noteId = currentNoteId ?: return
        val currentNote = _note.value ?: return
        
        viewModelScope.launch {
            _isSaving.value = true
            
            val updatedNote = currentNote.copy(
                title = _title.value,
                content = _content.value,
                updatedAt = Date()
            )
            
            // Use insertNote instead of updateNote to handle both insert and update
            repository.insertNote(updatedNote)
            _note.value = updatedNote
            
            _isSaving.value = false
        }
    }
    
    fun togglePinStatus() {
        val currentNote = _note.value ?: return
        
        viewModelScope.launch {
            val newPinStatus = !currentNote.isPinned
            repository.updatePinStatus(currentNote.id, newPinStatus)
            _note.value = currentNote.copy(isPinned = newPinStatus)
        }
    }
    
    fun deleteNote() {
        val noteId = currentNoteId ?: return
        
        viewModelScope.launch {
            repository.moveToTrash(noteId)
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        autoSaveJob?.cancel()
        // Save one final time when the ViewModel is cleared
        if (currentNoteId != null && (_title.value.isNotBlank() || _content.value.isNotBlank())) {
            viewModelScope.launch {
                saveNote()
            }
        }
    }
}
