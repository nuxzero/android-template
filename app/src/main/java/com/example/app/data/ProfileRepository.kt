package com.example.app.data

import com.example.app.data.models.Profile
import kotlinx.coroutines.flow.Flow


interface ProfileRepository {

    fun getProfile(): Flow<Profile>
}
