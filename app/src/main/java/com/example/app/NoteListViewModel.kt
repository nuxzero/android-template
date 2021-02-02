package com.example.app

import androidx.lifecycle.ViewModel
import com.example.app.data.NoteRepository
import com.example.app.data.NoteRepositoryImp
import com.example.app.data.models.Note

class NoteListViewModel(
    private val noteRepository: NoteRepository = NoteRepositoryImp()
) : ViewModel() {
    fun retrieveNotes(): List<Note> {
        return noteRepository.retrieveNoteList()
    }
}
