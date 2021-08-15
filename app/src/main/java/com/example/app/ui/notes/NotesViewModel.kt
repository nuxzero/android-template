package com.example.app.ui.notes

import androidx.lifecycle.ViewModel
import com.example.app.data.NoteRepository
import com.example.app.data.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    noteRepository: NoteRepository,
) : ViewModel() {
    val notes: Flow<List<Note>> = noteRepository.getNoteList()
}
