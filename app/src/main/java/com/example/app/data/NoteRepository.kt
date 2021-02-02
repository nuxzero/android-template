package com.example.app.data

import com.example.app.data.models.Note


interface NoteRepository {
    fun retrieveNoteList(): List<Note>
}
