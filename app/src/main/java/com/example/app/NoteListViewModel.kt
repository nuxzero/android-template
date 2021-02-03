package com.example.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.data.NoteRepository
import com.example.app.data.NoteRepositoryImp
import com.example.app.data.models.Note

class NoteListViewModel(
    private val noteRepository: NoteRepository = NoteRepositoryImp()
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    init {
        retrieveNotes()
    }

    fun retrieveNotes() {
        _notes.value = noteRepository.retrieveNoteList()
    }
}
