package com.example.app.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.app.data.ProfileRepository
import com.example.app.data.models.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val profileRepository: ProfileRepository) : ViewModel() {
    val profile: LiveData<Profile> = profileRepository.getProfile().asLiveData()
}
