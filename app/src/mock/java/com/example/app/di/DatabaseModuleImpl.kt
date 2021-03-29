package com.example.app.di

import android.content.Context
import androidx.room.Room
import com.example.app.data.database.AppDatabase
import com.example.app.data.database.NoteDao
import com.example.app.data.database.ProfileDao
import com.example.app.data.models.Note
import com.example.app.data.models.Profile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
object DatabaseModuleImpl : DatabaseModule {
    private const val DATABASE_NAME = "note_database"

    @Provides
    @Singleton
    override fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    override fun provideNoteDao(database: AppDatabase): NoteDao = object : NoteDao {
        override suspend fun getAll(): List<Note> {
            return MockData.getAllNotes()
        }

        override suspend fun findById(id: Int): Note {
            return MockData.getNote(id)
        }

        override suspend fun insertAll(vararg notes: Note) {
        }

        override suspend fun delete(note: Note) {
        }

    }

    @Provides
    @Singleton
    override fun provideProfileDao(database: AppDatabase): ProfileDao = object: ProfileDao {
        override suspend fun get(): Profile {
            return MockData.getProfile()
        }

        override suspend fun insert(profile: Profile) {
        }

        override suspend fun delete(profile: Profile) {
        }
    }
}
