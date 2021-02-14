package com.example.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.app.data.NoteRepository
import com.example.app.data.models.Note
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel constructor(
    @Inject private val noteRepository: NoteRepository,
    @Assisted val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val notes: LiveData<List<Note>> = noteRepository.getNoteList().asLiveData()
}
