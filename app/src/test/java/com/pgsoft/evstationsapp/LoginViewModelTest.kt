package com.pgsoft.evstationsapp

import com.pgsoft.evstationsapp.features.login.LoginUiState
import com.pgsoft.evstationsapp.features.login.LoginViewModel
import junit.framework.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        //viewModel = LoginViewModel()
    }

    @Test
    fun uiState_whenInitialized_thenDefault() {
        assertEquals(LoginUiState.Default(), viewModel.uiState.value)
    }

    @Test
    fun uiState_whenUserNameIsBlank_thenError() {
        viewModel.login("", "password")
        assertNotEquals((viewModel.uiState.value as LoginUiState.Default).error, null)
    }

    @Test
    fun uiState_whenPasswordIsBlank_thenError() {
        viewModel.login("user", "")
        assertNotEquals((viewModel.uiState.value as LoginUiState.Default).error, null)
    }

    @Test
    fun uiState_whenUsernameAndPasswordAreBlank_thenError() {
        viewModel.login("", "")
        assertNotEquals((viewModel.uiState.value as LoginUiState.Default).error, null)
    }

    @Test
    fun  uiState_whenCredentialsIncorrect_thenError() {
        assertTrue(false)
    }

    @Test
    fun  uiState_whenCredentialsCorrect_thenNavigateToLogin() {
        assertTrue(false)
    }
}