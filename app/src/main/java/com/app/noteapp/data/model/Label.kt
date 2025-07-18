package com.app.noteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labels")
data class Label(
    @PrimaryKey
    val id: String,
    val name: String,
    val color: String = "default"
)
