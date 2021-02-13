package com.example.app.data

import com.example.app.data.models.Note
import kotlinx.coroutines.flow.Flow
import java.util.Date


class NoteRepositoryImp : NoteRepository {
    override fun getNoteList(): Flow<List<Note>> {
        TODO()
    }

    override fun getNote(id: Int): Flow<Note> {
        TODO()
    }
}
