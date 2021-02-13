package com.example.app.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date


@Parcelize
data class Note(
    val id: Int,
    val title: String,
    val author: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val image: String,
    val note: String,
): Parcelable
