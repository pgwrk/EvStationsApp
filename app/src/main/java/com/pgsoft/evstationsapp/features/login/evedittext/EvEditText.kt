package com.pgsoft.evstationsapp.features.login.evedittext

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pgsoft.evstationsapp.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EvEditText(
    @DrawableRes iconId: Int,
    label: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = remember { mutableStateOf( EvEditTextState(keyboardType) ) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(
        value = state.value.text,
        onValueChange = { newText ->
            state.update { copy(text = newText) }
            onTextChange(newText)
       },
        label = {
            Text(text = label)
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focus ->
                state.update { copy(isFocused = focus.isFocused) }
            },
        singleLine = true,
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(start = 0.dp, end = 32.dp),
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        },
        trailingIcon = {
            state.value.getTrailingIcon()?.let { icon ->
                IconButton(
                    onClick = { state.update { onTrailingIconClicked() } }
                ){
                    Icon(
                        imageVector = icon,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null)
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.onBackground
        ),
        visualTransformation = state.value.getVisualTransition(),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        )
    )
}

private fun<T> MutableState<T>.update(block: T.() -> T) {
    value = block(value)
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun EvEditTextPreview() = MaterialTheme{
    EvEditText(
        iconId = R.drawable.ic_person,
        label = "User name",
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Email,
        onTextChange = {}
    )
}