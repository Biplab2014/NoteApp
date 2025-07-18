package com.app.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.noteapp.ui.screens.HomeScreen
import com.app.noteapp.ui.screens.NoteEditorScreen
import com.app.noteapp.ui.screens.TrashScreen

@Composable
fun NoteNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onNavigateToEditor = { noteId ->
                    if (noteId != null) {
                        navController.navigate("editor/$noteId")
                    } else {
                        navController.navigate("editor/new")
                    }
                },
                onNavigateToTrash = {
                    navController.navigate("trash")
                }
            )
        }
        
        composable("editor/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            val actualNoteId = if (noteId == "new") null else noteId
            
            NoteEditorScreen(
                noteId = actualNoteId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("trash") {
            TrashScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
