package com.example.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.account.AccountContent
import com.example.app.ui.note_detail.NoteDetailContent
import com.example.app.ui.notes.NotesContent
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeSections.HOME.route,
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        composable(HomeSections.HOME.route) {
            NotesContent { note ->
                navController.navigate(NavigationRoute.NoteDetail.createRoute(note.id))
            }
        }

        composable(HomeSections.ACCOUNT.route) {
            AccountContent()
        }

        composable(
            route = NavigationRoute.NoteDetail.route,
            arguments = listOf(
                navArgument(NavigationRoute.NoteDetail.NOTE_ID_ARG) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(NavigationRoute.NoteDetail.NOTE_ID_ARG)?.let { noteId ->
                NoteDetailContent(noteId) {
                    navController.navigateUp()
                }
            }
        }
    }
}
