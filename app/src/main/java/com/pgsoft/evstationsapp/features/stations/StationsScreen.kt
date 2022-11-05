package com.pgsoft.evstationsapp.features.stations

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import  androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun StationsRoute() {
    StationsScreen()
}

@Composable
fun StationsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Stations list screen")
    }
}

@Preview(widthDp = 360, heightDp = 720)
@Preview(device = Devices.PIXEL_3, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StationsScreenPreview() = EvStationsAppTheme {
    StationsScreen()
}