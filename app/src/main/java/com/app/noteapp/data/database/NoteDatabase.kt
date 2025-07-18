package com.app.noteapp.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.app.noteapp.data.dao.LabelDao
import com.app.noteapp.data.dao.NoteDao
import com.app.noteapp.data.dao.ReminderDao
import com.app.noteapp.data.model.Label
import com.app.noteapp.data.model.Note
import com.app.noteapp.data.model.NoteLabel
import com.app.noteapp.data.model.Reminder

@Database(
    entities = [Note::class, Label::class, NoteLabel::class, Reminder::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    
    abstract fun noteDao(): NoteDao
    abstract fun labelDao(): LabelDao
    abstract fun reminderDao(): ReminderDao
    
    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        
        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
