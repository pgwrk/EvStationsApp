package com.pgsoft.evstationsapp.features.login.evedittext

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class EvEditTextState(
    val keyboardType: KeyboardType = KeyboardType.Text,
    val text: String = "",
    val isFocused: Boolean = false,
    val textVisible: Boolean = keyboardType != KeyboardType.Password
) {
    fun getVisualTransition(): VisualTransformation =
        if (textVisible) VisualTransformation.None else PasswordVisualTransformation()

    fun getTrailingIcon() : ImageVector? =
        when {
            text.isEmpty() -> null
            isFocused -> Icons.Default.Close
            keyboardType == KeyboardType.Password -> {
                if (textVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            }
            else -> null
        }

    fun onTrailingIconClicked(): EvEditTextState =
        when {
            isFocused -> copy(text = "")
            else -> copy(textVisible = !textVisible)
        }
}