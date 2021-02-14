package com.example.app.data

import com.example.app.data.models.Note
import com.example.app.data.network.NoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NoteRepositoryImp @Inject constructor(private val noteApi: NoteApi) : NoteRepository {
    override fun getNoteList(): Flow<List<Note>> {
        return flow { emit(noteApi.getNoteList()) }
    }

    override fun getNote(id: Int): Flow<Note> {
        return flow { emit(noteApi.getNote(id)) }
    }
}
