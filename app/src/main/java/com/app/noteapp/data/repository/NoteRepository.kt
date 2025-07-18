package com.app.noteapp.data.repository

import com.app.noteapp.data.dao.LabelDao
import com.app.noteapp.data.dao.NoteDao
import com.app.noteapp.data.dao.ReminderDao
import com.app.noteapp.data.model.Label
import com.app.noteapp.data.model.Note
import com.app.noteapp.data.model.NoteLabel
import com.app.noteapp.data.model.Reminder
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val labelDao: LabelDao,
    private val reminderDao: ReminderDao
) {
    
    // Note operations
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
    
    fun getDeletedNotes(): Flow<List<Note>> = noteDao.getDeletedNotes()
    
    suspend fun getNoteById(id: String): Note? = noteDao.getNoteById(id)
    
    fun searchNotes(query: String): Flow<List<Note>> = noteDao.searchNotes(query)
    
    fun getNotesByColor(colorTag: String): Flow<List<Note>> = noteDao.getNotesByColor(colorTag)
    
    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    
    suspend fun updatePinStatus(id: String, isPinned: Boolean) = noteDao.updatePinStatus(id, isPinned)
    
    suspend fun moveToTrash(id: String) = noteDao.moveToTrash(id, Date().time)
    
    suspend fun restoreFromTrash(id: String) = noteDao.restoreFromTrash(id)
    
    suspend fun permanentlyDeleteNote(id: String) = noteDao.permanentlyDeleteNote(id)
    
    suspend fun deleteOldTrashedNotes() {
        val cutoffTime = Date().time - (7 * 24 * 60 * 60 * 1000) // 7 days ago
        noteDao.deleteOldTrashedNotes(cutoffTime)
    }
    
    // Label operations
    fun getAllLabels(): Flow<List<Label>> = labelDao.getAllLabels()
    
    suspend fun getLabelById(id: String): Label? = labelDao.getLabelById(id)
    
    fun searchLabels(query: String): Flow<List<Label>> = labelDao.searchLabels(query)
    
    suspend fun insertLabel(label: Label) = labelDao.insertLabel(label)
    
    suspend fun updateLabel(label: Label) = labelDao.updateLabel(label)
    
    suspend fun deleteLabel(label: Label) = labelDao.deleteLabel(label)
    
    suspend fun addLabelToNote(noteId: String, labelId: String) {
        labelDao.insertNoteLabel(NoteLabel(noteId, labelId))
    }
    
    suspend fun removeLabelFromNote(noteId: String, labelId: String) {
        labelDao.deleteNoteLabel(noteId, labelId)
    }
    
    suspend fun removeAllLabelsFromNote(noteId: String) {
        labelDao.deleteAllNoteLabels(noteId)
    }
    
    fun getLabelsForNote(noteId: String): Flow<List<Label>> = labelDao.getLabelsForNote(noteId)
    
    fun getNotesForLabel(labelId: String): Flow<List<Note>> = labelDao.getNotesForLabel(labelId)
    
    // Reminder operations
    fun getAllReminders(): Flow<List<Reminder>> = reminderDao.getAllReminders()
    
    fun getRemindersForNote(noteId: String): Flow<List<Reminder>> = reminderDao.getRemindersForNote(noteId)
    
    suspend fun getReminderById(id: String): Reminder? = reminderDao.getReminderById(id)
    
    suspend fun getDueReminders(): List<Reminder> = reminderDao.getDueReminders(Date().time)
    
    suspend fun insertReminder(reminder: Reminder) = reminderDao.insertReminder(reminder)
    
    suspend fun updateReminder(reminder: Reminder) = reminderDao.updateReminder(reminder)
    
    suspend fun updateReminderStatus(id: String, isCompleted: Boolean) = 
        reminderDao.updateReminderStatus(id, isCompleted)
    
    suspend fun deleteReminder(reminder: Reminder) = reminderDao.deleteReminder(reminder)
    
    suspend fun deleteRemindersForNote(noteId: String) = reminderDao.deleteRemindersForNote(noteId)
}
