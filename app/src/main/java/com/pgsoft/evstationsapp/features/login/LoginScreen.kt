package com.pgsoft.evstationsapp.features.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun LoginRoute(onLoggedIn: () -> Unit) {
    LoginScreen(
        onLoggedIn = onLoggedIn
    )
}

@Composable
fun LoginScreen(onLoggedIn: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            onClick = { onLoggedIn() }
        ) {
            Text(text = stringResource(R.string.login_button))
        }
    }
}

@Preview(device = Devices.PIXEL_3)
@Preview(device = Devices.PIXEL_3, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LoginScreenPreview() = EvStationsAppTheme {
    LoginScreen {}
}