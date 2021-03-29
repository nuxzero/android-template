package com.example.app.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.app.data.NoteRepository
import com.example.app.data.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {

    val notes: LiveData<List<Note>> = noteRepository.getNoteList().asLiveData()
}
