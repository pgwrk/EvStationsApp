package com.pgsoft.evstationsapp.features.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun LoginRoute(
    onLoggedIn: () -> Unit,
    onClose: () -> Unit
) {
    LoginScreen(
        onLoggedIn = onLoggedIn,
        onClose = onClose,
        onLoginTapped = {}
    )
}

@Composable
fun LoginScreen(
    onLoggedIn: () -> Unit,
    onClose: () -> Unit,
    onLoginTapped: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val (header, image, loginModule, loginButton) = createRefs()

        Header(
            modifier = Modifier.constrainAs(header){},
            onClose = onClose
        )

        Icon(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(header.bottom)
                    bottom.linkTo(loginModule.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            painter = painterResource(id = R.drawable.log_in),
            tint = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
            contentDescription = null
        )

        LoginModule(
            modifier = Modifier.constrainAs(loginModule) {
                top.linkTo(header.bottom)
                bottom.linkTo(loginButton.top)
            }
        )

        LoginButton(
            showProgress = false,
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
private fun LoginModule(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(150.dp)
        .background(Color.DarkGray)
    ) { }
}

@Composable
private fun LoginButton(
    showProgress: Boolean,
    onClick: ()->Unit,
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

@Preview(device = Devices.PIXEL_3)
@Preview(device = Devices.PIXEL_3, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() = EvStationsAppTheme {
    LoginScreen({}, {}, {})
}