package com.example.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app.data.models.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id == :id")
    suspend fun findById(id: Int): Note

    @Insert
    suspend fun insertAll(vararg notes: Note)

    @Delete
    suspend fun delete(note: Note)
}
