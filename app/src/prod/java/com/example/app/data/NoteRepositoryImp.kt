package com.example.app.data

import com.example.app.data.models.Note
import com.example.app.data.network.NoteApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun provideRetrofit(): Retrofit {
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()
    return Retrofit.Builder()
        .client(client)
        .baseUrl("https://blooming-falls-95246.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

class NoteRepositoryImp(private val noteApi: NoteApi = provideRetrofit().create(NoteApi::class.java)) : NoteRepository {
    override fun getNoteList(): Flow<List<Note>> {
        return flow { emit(noteApi.getNoteList()) }
    }

    override fun getNote(id: Int): Flow<Note> {
        return flow { emit(noteApi.getNote(id)) }
    }
}
