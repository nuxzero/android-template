package com.example.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app.data.models.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id == :id")
    fun findById(id: Int): Note

    @Insert
    fun insertAll(vararg notes: Note)

    @Delete
    fun delete(note: Note)
}
