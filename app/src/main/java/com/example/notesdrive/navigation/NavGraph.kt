package com.example.notesdrive.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notesdrive.data.Note
import com.example.notesdrive.screens.AddNoteScreen
import com.example.notesdrive.screens.MainScreen
import com.example.notesdrive.screens.NoteCard
import com.example.notesdrive.screens.UpdateNoteScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    note: Note
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(
            route = Screen.Main.route
        ) {
            MainScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }
        composable(
            route = Screen.AddScreen.route
        ) {
            AddNoteScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }
        composable(
            route = Screen.UpdateScreen.route
        ) {
            UpdateNoteScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }
    }
}