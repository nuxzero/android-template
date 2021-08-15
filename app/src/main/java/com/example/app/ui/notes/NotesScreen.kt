package com.example.app.ui.notes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.data.models.Note
import com.example.app.ui.components.AppTopBar
import com.example.app.ui.theme.AppTheme
import java.util.Date


@Composable
fun NotesContent(onItemClicked: (Note) -> Unit) {
    val viewModel: NotesViewModel = hiltViewModel()
    val notes by viewModel.notes.collectAsState(initial = listOf())
    NotesContent(notes, onItemClicked)
}

@Composable
fun NotesContent(notes: List<Note>, onItemClicked: (Note) -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar(title = "Home")
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(notes) { item ->
                    NoteItem(item, onItemClick = onItemClicked)
                }
            }
        }
    )
}

@OptIn(ExperimentalStdlibApi::class)
@Preview
@Composable
fun NotesScreenPreview() {
    val notes = buildList {
        repeat(10) { i ->
            val note = Note(
                id = i,
                title = "$i sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                author = "John Doe",
                createdAt = Date(),
                image = "https://picsum.photos/id/486/1280/720",
                note = ""
            )
            add(note)
        }
    }

    AppTheme {
        NotesContent(notes = notes, onItemClicked = {})
    }
}
