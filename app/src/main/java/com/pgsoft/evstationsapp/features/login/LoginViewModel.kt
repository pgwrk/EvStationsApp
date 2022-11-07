package com.pgsoft.evstationsapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Default())
    val uiState : StateFlow<LoginUiState> = _uiState

    fun login(userName: String, password: String) {
        if (isLoadingNow()) return

        viewModelScope.launch {
            setState(LoginUiState.Default(isLoading = true))
            delay(3000)
            setState(LoginUiState.Default(error = "Something went wrong"))
        }
    }

    fun clearError() {
        val newState = (_uiState.value as LoginUiState.Default).copy(error = null)
        setState(newState)
    }

    private fun setState(newState: LoginUiState) {
        _uiState.value = newState
    }

    private fun isLoadingNow(): Boolean =
        (_uiState.value as LoginUiState.Default).isLoading
}