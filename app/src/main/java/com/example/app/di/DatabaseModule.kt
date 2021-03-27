package com.example.app.di

import android.content.Context
import com.example.app.data.database.AppDatabase
import com.example.app.data.database.NoteDao
import com.example.app.data.database.ProfileDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier

interface DatabaseModule {
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase
    fun provideNoteDao(database: AppDatabase): NoteDao
    fun provideProfileDao(database: AppDatabase): ProfileDao
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NoteDaoAnnotation
