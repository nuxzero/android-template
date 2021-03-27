package com.example.app.data

import com.example.app.data.models.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    fun getNoteList(): Flow<List<Note>>
    fun getNote(id: Int): Flow<Note>
}
