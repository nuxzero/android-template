package com.example.app.ui.note_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.app.data.NoteRepository
import com.example.app.data.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val noteId = MutableLiveData<Int>()
    val note: Flow<Note> = noteId.switchMap { id ->
        repository.getNote(id).asLiveData()
    }.asFlow()

    fun setNoteId(id: Int) {
        noteId.value = id
    }
}
