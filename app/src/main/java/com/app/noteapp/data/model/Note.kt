package com.app.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Date,
    val updatedAt: Date,
    val isPinned: Boolean = false,
    val colorTag: String = "default", // default, red, orange, yellow, green, blue, purple, pink, gray
    val isDeleted: Boolean = false,
    val deletedAt: Date? = null
)
