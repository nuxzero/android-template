package com.example.app.di

import com.example.app.data.NoteRepositoryImpl
import com.example.app.data.database.NoteDao
import com.example.app.data.network.NoteApi


interface NoteRepositoryModule {
    fun provideNoteRepository(noteApi: NoteApi, noteDao: NoteDao): NoteRepositoryImpl
}
