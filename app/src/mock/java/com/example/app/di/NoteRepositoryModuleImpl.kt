package com.example.app.di

import com.example.app.data.NoteRepositoryImpl
import com.example.app.data.database.NoteDao
import com.example.app.data.network.NoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NoteRepositoryModuleImpl : NoteRepositoryModule {

    @Singleton
    @Provides
    override fun provideNoteRepository(
        @NoteApiAnnotation noteApi: NoteApi,
        @NoteDaoAnnotation noteDao: NoteDao
    ): NoteRepositoryImpl {
        return NoteRepositoryImpl(noteApi, noteDao)
    }
}
