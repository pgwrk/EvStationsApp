package com.pgsoft.evstationsapp.features.login

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.view.ViewTreeObserver
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.features.login.evedittext.EvEditText
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun LoginRoute(
    onLoggedIn: () -> Unit,
    onClose: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    when (uiState) {
        LoginUiState.LoggedIn -> onLoggedIn()
        is LoginUiState.Default -> LoginScreen(
            uiState = uiState as LoginUiState.Default,
            onClose = onClose,
            onUserNameChanged = { newUserName -> userName.value = newUserName },
            onPasswordChanged = { newPassword -> password.value = newPassword },
            onLoginTapped = { viewModel.login(userName.value, password.value) }
        )
    }
}

@Composable
fun LoginScreen(
    uiState: LoginUiState.Default,
    onClose: () -> Unit,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginTapped: () -> Unit
) {
    val imageVisible = remember { mutableStateOf( true ) }
    val imageAlpha: Float by animateFloatAsState(
        targetValue = if (imageVisible.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing,
        )
    )

    observerKeyboardVisibility { keyboardVisible ->
        imageVisible.value = !keyboardVisible
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val (header, image, loginModule, loginButton, error) = createRefs()

        Header(
            modifier = Modifier.constrainAs(header) {},
            onClose = onClose
        )

        Image(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(loginModule.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.log_in),
            alpha = imageAlpha,
            contentDescription = null
        )

        LoginModule(
            modifier = Modifier.constrainAs(loginModule) {
                top.linkTo(header.bottom)
                bottom.linkTo(loginButton.top)
            },
            onUserNameChanged = onUserNameChanged,
            onPasswordChanged = onPasswordChanged
        )

        uiState.error?.let {
            Text(
                text = it.resolve(LocalContext.current),
                modifier = Modifier
                    .constrainAs(error) {
                        bottom.linkTo(loginButton.top, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        }

        LoginButton(
            showProgress = uiState.isLoading,
            onClick = onLoginTapped,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(loginButton) {
                    bottom.linkTo(parent.bottom)
                    height = Dimension.value(56.dp)
                }
        )
    }
}

@Composable
private fun Header(
    onClose: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp),
                text = stringResource(id = R.string.login_title),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )

            IconButton(onClick = onClose) {
                Icon(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_x),
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )
            }
        }

        Divider(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun LoginModule(
    modifier: Modifier,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
    ) {
        EvEditText(
            iconId = R.drawable.ic_person,
            label = stringResource(id = R.string.login_username),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            { onUserNameChanged(it) })

        EvEditText(
            iconId = R.drawable.ic_lock,
            label = stringResource(id = R.string.login_password),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            { onPasswordChanged(it) })

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = {},
                contentPadding = PaddingValues(0.dp)

            ) {
                Text(
                    text = stringResource(id = R.string.login_forgot_password),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
            TextButton(
                onClick = {},
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.login_register),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Composable
private fun LoginButton(
    showProgress: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = Color.Black
        ),
        contentPadding = ButtonDefaults.TextButtonContentPadding
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.login_button),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
            )

            if (showProgress) {
                CircularProgressIndicator(
                    color = Color.DarkGray,
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(16.dp)
                        .align(Alignment.CenterVertically)

                )
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
private fun observerKeyboardVisibility(onVisibilityChanged: (shown: Boolean) -> Unit) {
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)?.isVisible(WindowInsetsCompat.Type.ime()) ?: true

            onVisibilityChanged(isKeyboardOpen)
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}

@Preview(device = Devices.PIXEL_3)
@Preview(device = Devices.PIXEL_3, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() = EvStationsAppTheme {
    LoginScreen(uiState = LoginUiState.Default(), {},{}, {}, {})
}