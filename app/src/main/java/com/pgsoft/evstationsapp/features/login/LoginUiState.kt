package com.pgsoft.evstationsapp.features.login

import com.pgsoft.evstationsapp.data.common.EvText

sealed class LoginUiState {

    data class Default(
        val isLoading: Boolean = false,
        val error: EvText? = null
    ): LoginUiState()

    object LoggedIn : LoginUiState()
}