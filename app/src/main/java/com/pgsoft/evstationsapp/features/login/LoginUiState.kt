package com.pgsoft.evstationsapp.features.login

import com.pgsoft.evstationsapp.data.common.Text

sealed class LoginUiState {

    data class Default(
        val isLoading: Boolean = false,
        val error: Text? = null
    ): LoginUiState()

    object LoggedIn : LoginUiState()
}