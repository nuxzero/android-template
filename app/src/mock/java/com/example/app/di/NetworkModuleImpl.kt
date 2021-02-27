package com.example.app.di

import com.example.app.data.models.Note
import com.example.app.data.models.Profile
import com.example.app.data.network.NoteApi
import com.example.app.data.network.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModuleImpl : NetworkModule {

    private const val HOST_NAME = "https://blooming-falls-95246.herokuapp.com/"

    @Provides
    @Singleton
    override fun provideRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(HOST_NAME)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    override fun provideNoteApi(retrofit: Retrofit): NoteApi = object : NoteApi {
        override suspend fun getNoteList(): List<Note> {
            return MockData.getAllNotes()
        }

        override suspend fun getNote(id: Int): Note {
            return MockData.getNote(id)
        }
    }

    @Provides
    override fun provideProfileApi(retrofit: Retrofit): ProfileApi = object : ProfileApi {
        override suspend fun getProfile(): Profile {
            return MockData.getProfile()
        }
    }
}
