package com.example.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.app.data.NoteRepositoryImp
import com.example.app.data.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepositoryImp,
) : ViewModel() {

    val notes: LiveData<List<Note>> = noteRepository.getNoteList().asLiveData()
}
