package com.app.noteapp.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

// Note color tags
val NoteColorDefault = Color(0xFFFFFBFE)
val NoteColorRed = Color(0xFFFFEBEE)
val NoteColorOrange = Color(0xFFFFF3E0)
val NoteColorYellow = Color(0xFFFFFDE7)
val NoteColorGreen = Color(0xFFE8F5E8)
val NoteColorBlue = Color(0xFFE3F2FD)
val NoteColorPurple = Color(0xFFF3E5F5)
val NoteColorPink = Color(0xFFFCE4EC)
val NoteColorGray = Color(0xFFF5F5F5)

fun getNoteColor(colorTag: String): Color {
    return when (colorTag) {
        "red" -> NoteColorRed
        "orange" -> NoteColorOrange
        "yellow" -> NoteColorYellow
        "green" -> NoteColorGreen
        "blue" -> NoteColorBlue
        "purple" -> NoteColorPurple
        "pink" -> NoteColorPink
        "gray" -> NoteColorGray
        else -> NoteColorDefault
    }
}