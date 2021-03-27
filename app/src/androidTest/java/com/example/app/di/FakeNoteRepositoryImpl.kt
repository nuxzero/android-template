package com.example.app.di

import com.example.app.data.NoteRepository
import com.example.app.data.models.Note
import kotlinx.coroutines.flow.Flow


class FakeNoteRepositoryImpl : NoteRepository {
    override fun getNoteList(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun getNote(id: Int): Flow<Note> {
        TODO("Not yet implemented")
    }
}
