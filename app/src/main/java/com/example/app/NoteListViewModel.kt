package com.example.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.app.data.NoteRepository
import com.example.app.data.NoteRepositoryImp
import com.example.app.data.models.Note

class NoteListViewModel(
    private val noteRepository: NoteRepository = NoteRepositoryImp()
) : ViewModel() {

    val notes: LiveData<List<Note>> = noteRepository.getNoteList().asLiveData()
}
