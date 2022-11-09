package com.pgsoft.evstationsapp.features.stations.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.data.common.EvText
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun ErrorScreen(
    description: EvText,
    onRetry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = description.resolve(LocalContext.current),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 32.dp),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )

            TextButton(
                onClick = onRetry,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.stations_retry_button))
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingScreenPreview() = EvStationsAppTheme {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface.copy(alpha = 0.9f))
    ) {
        ErrorScreen(
            description = EvText.PlainText("There is no internet connection"),
            onRetry = {}
        )
    }
}