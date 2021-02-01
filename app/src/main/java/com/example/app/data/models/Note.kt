package com.example.app.data.models

import java.util.*


data class Note(
    val id: Int,
    val title: String,
    val author: String,
    val createdAt: Date,
    val image: String,
    val note: String,
)
