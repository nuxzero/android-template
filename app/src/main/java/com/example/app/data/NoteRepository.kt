package com.example.app.data

import com.example.app.data.database.NoteDao
import com.example.app.data.models.Note
import com.example.app.data.network.NoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NoteRepository @Inject constructor(
    private val noteApi: NoteApi,
    private val noteDao: NoteDao,
) {
    fun getNoteList(): Flow<List<Note>> = flow {
        val cachedNotes = noteDao.getAll()
        emit(cachedNotes)

        val networkNotes = noteApi.getNoteList()
        emit(networkNotes)
        noteDao.insertAll(*networkNotes.toTypedArray())
    }

    fun getNote(id: Int): Flow<Note> = flow {
        emit(noteDao.findById(id))
        val note = noteApi.getNote(id)
        emit(note)
        noteDao.insertAll(note)
    }
}
