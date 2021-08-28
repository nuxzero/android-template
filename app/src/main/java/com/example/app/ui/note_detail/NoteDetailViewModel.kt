package com.example.app.ui.note_detail

import androidx.lifecycle.ViewModel
import com.example.app.data.NoteRepository
import com.example.app.data.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(repository: NoteRepository) : ViewModel() {
    private val noteId = MutableStateFlow(-1)
    val note: Flow<Note> = repository.getNote(noteId.value)

    fun setNoteId(id: Int) {
        noteId.value = id
    }
}
