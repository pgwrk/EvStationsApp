package com.pgsoft.evstationsapp.features.login

sealed class LoginUiState {

    class Default(
        val isLoading: Boolean = false,
        val error: String? = null
    ): LoginUiState()

    object LoggedIn : LoginUiState()
}