package com.example.app.data

import com.example.app.data.models.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton


@Singleton
class ProfileRepositoryImp : ProfileRepository {
    override fun getProfile(): Flow<Profile> = flow {
        emit(
            Profile(
                id = 1,
                fullName = "John Doe",
                email = "",
                image = "https://images.unsplash.com/photo-1588571590924-433cc2020a12?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=668&q=80",
            )
        )
    }
}
