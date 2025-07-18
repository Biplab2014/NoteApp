package com.app.noteapp.data.dao

import androidx.room.*
import com.app.noteapp.data.model.Label
import com.app.noteapp.data.model.NoteLabel
import kotlinx.coroutines.flow.Flow

@Dao
interface LabelDao {
    
    @Query("SELECT * FROM labels ORDER BY name ASC")
    fun getAllLabels(): Flow<List<Label>>
    
    @Query("SELECT * FROM labels WHERE id = :id")
    suspend fun getLabelById(id: String): Label?
    
    @Query("SELECT * FROM labels WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchLabels(query: String): Flow<List<Label>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLabel(label: Label)
    
    @Update
    suspend fun updateLabel(label: Label)
    
    @Delete
    suspend fun deleteLabel(label: Label)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteLabel(noteLabel: NoteLabel)
    
    @Query("DELETE FROM note_labels WHERE noteId = :noteId AND labelId = :labelId")
    suspend fun deleteNoteLabel(noteId: String, labelId: String)
    
    @Query("DELETE FROM note_labels WHERE noteId = :noteId")
    suspend fun deleteAllNoteLabels(noteId: String)
    
    @Query("""
        SELECT l.* FROM labels l 
        INNER JOIN note_labels nl ON l.id = nl.labelId 
        WHERE nl.noteId = :noteId
        ORDER BY l.name ASC
    """)
    fun getLabelsForNote(noteId: String): Flow<List<Label>>
    
    @Query("""
        SELECT n.* FROM notes n 
        INNER JOIN note_labels nl ON n.id = nl.noteId 
        WHERE nl.labelId = :labelId AND n.isDeleted = 0
        ORDER BY n.isPinned DESC, n.updatedAt DESC
    """)
    fun getNotesForLabel(labelId: String): Flow<List<com.app.noteapp.data.model.Note>>
}
