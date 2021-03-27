package com.example.app.di

import com.example.app.data.network.NoteApi
import com.example.app.data.network.ProfileApi
import retrofit2.Retrofit
import javax.inject.Qualifier

interface NetworkModule {
    fun provideRetrofit(): Retrofit
    fun provideNoteApi(retrofit: Retrofit): NoteApi
    fun provideProfileApi(retrofit: Retrofit): ProfileApi
}
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NoteApiAnnotation