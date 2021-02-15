package com.example.app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.app.data.models.Profile


@Dao
interface ProfileDao {

    @Query("SELECT * FROM note LIMIT 1")
    fun get(): Profile

    @Insert
    fun insert(profile: Profile)

    @Delete
    fun delete(profile: Profile)
}
