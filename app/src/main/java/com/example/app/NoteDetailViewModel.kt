package com.example.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.app.data.NoteRepository
import com.example.app.data.NoteRepositoryImp
import com.example.app.data.models.Note

class NoteDetailViewModel(private val repository: NoteRepository = NoteRepositoryImp()) : ViewModel() {
    private val noteId = MutableLiveData<Int>()
    val note: LiveData<Note> = noteId.switchMap { id ->
        repository.getNote(id).asLiveData()
    }

    fun setNoteId(id: Int) {
        noteId.value = id
    }
}
