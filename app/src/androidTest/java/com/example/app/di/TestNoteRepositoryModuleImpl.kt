package com.example.app.di

import com.example.app.data.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NoteRepositoryModuleImpl::class]
)
abstract class TestNoteRepositoryModuleImpl : NoteRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repo: FakeNoteRepositoryImpl): NoteRepositoryImpl
}
