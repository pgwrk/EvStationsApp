package com.pgsoft.evstationsapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.data.common.Text
import com.pgsoft.evstationsapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Default())
    val uiState : StateFlow<LoginUiState> = _uiState

    fun login(userName: String, password: String) {
        if (isLoadingNow()) return

        if (!CredentialsChecker(userName, password).isValid()) {
            setState(LoginUiState.Default(error = Text.ResText(R.string.login_credentials_invalid)))
        } else {
            viewModelScope.launch {
                setState(LoginUiState.Default(isLoading = true))
                val response = authRepository.login(userName, password)
                if (response.isSuccess) {
                    setState(LoginUiState.LoggedIn)
                } else {
                    val errorDescription = response.exceptionOrNull()?.message?.let {
                        Text.PlainText(it)
                    }  ?: run {
                        Text.ResText(R.string.login_common_error)
                    }
                    setState(LoginUiState.Default(error = errorDescription))
                }
            }
        }
    }

    private fun setState(newState: LoginUiState) {
        _uiState.value = newState
    }

    private fun isLoadingNow(): Boolean =
        (_uiState.value as LoginUiState.Default).isLoading
}

// May be injected and must be more complex
private class CredentialsChecker(val userName: String, val password: String) {
    fun isValid(): Boolean {
        return userName.isNotBlank() && password.isNotBlank()
    }
}