package com.app.noteapp.di

import android.content.Context
import androidx.room.Room
import com.app.noteapp.data.dao.LabelDao
import com.app.noteapp.data.dao.NoteDao
import com.app.noteapp.data.dao.ReminderDao
import com.app.noteapp.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }
    
    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao = database.noteDao()
    
    @Provides
    fun provideLabelDao(database: NoteDatabase): LabelDao = database.labelDao()
    
    @Provides
    fun provideReminderDao(database: NoteDatabase): ReminderDao = database.reminderDao()
}
