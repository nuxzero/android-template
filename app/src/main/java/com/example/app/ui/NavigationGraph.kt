package com.example.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.data.models.Note
import com.example.app.data.models.Profile
import com.example.app.ui.account.AccountContent
import com.example.app.ui.note_detail.NoteDetailContent
import com.example.app.ui.notes.NotesContent


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeSections.HOME.route,
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        // TODO: val viewModel  = ...
        composable(HomeSections.HOME.route) {
            val notes = listOf<Note>()
            NotesContent(notes) { note ->

            }
        }

        composable(HomeSections.ACCOUNT.route) {
            // TODO: val viewModel  = ...
            val profile = Profile(
                id = 1,
                fullName = "John Doe",
                email = "john@mail.com",
                image = "https://picsum.photos/id/433/300/300",
            )
            AccountContent(profile)
        }

        composable("NoteDetail") {
            // TODO: val viewModel  = ...
            val note = TODO()
            NoteDetailContent(note) {

            }
        }
    }
}
