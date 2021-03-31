package com.example.app.ui.note_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.app.data.NoteRepository
import com.example.app.data.models.Note
import javax.inject.Inject

class NoteDetailViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val noteId = MutableLiveData<Int>()
    val note: LiveData<Note> = noteId.switchMap { id ->
        repository.getNote(id).asLiveData()
    }

    fun setNoteId(id: Int) {
        noteId.value = id
    }
}
