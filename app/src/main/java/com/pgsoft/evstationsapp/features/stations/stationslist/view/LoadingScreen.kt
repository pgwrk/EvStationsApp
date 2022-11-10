package com.pgsoft.evstationsapp.features.stations.stationslist.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier.align(Alignment.Center)){
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                strokeWidth = 2.dp,
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = stringResource(id = R.string.stations_loading),
                modifier = Modifier.padding(start = 16.dp),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle1,
            )
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
        LoadingScreen()
    }
}