package com.example.app.di

import com.example.app.data.NoteRepository
import com.example.app.data.database.NoteDao
import com.example.app.data.network.NoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NoteRepositoryModuleImpl : NoteRepositoryModule{

    @Singleton
    @Provides
    override fun provideNoteRepository(noteApi: NoteApi, noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteApi, noteDao)
    }
}
