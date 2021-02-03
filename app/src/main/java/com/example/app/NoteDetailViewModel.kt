package com.example.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.data.NoteRepository
import com.example.app.data.NoteRepositoryImp
import com.example.app.data.models.Note

class NoteDetailViewModel(private val repository: NoteRepository = NoteRepositoryImp()) : ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note: LiveData<Note> = _note

    fun retrieveNote(id: Int) {
        _note.value = repository.retrieveNote(id)
    }
}
