package com.example.app.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity
data class Profile(
    val email: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    val image: String,
)
