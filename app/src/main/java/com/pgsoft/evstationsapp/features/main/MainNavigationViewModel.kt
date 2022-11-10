package com.pgsoft.evstationsapp.features.main

import androidx.lifecycle.ViewModel
import com.pgsoft.evstationsapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNavigationViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    fun hasToken(): Boolean {
        val token = authRepository.getToken()
        return !token.isNullOrBlank()
    }
}