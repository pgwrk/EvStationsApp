package com.pgsoft.evstationsapp.features.login

sealed class LoginUiState {

    data class Default(
        val isLoading: Boolean = false,
        val error: String? = null
    ): LoginUiState()

    object LoggedIn : LoginUiState()
}