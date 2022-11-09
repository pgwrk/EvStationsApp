package com.pgsoft.evstationsapp.features.stations.view

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.data.common.EvText
import com.pgsoft.evstationsapp.features.stations.UiStation
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun ContentScreen(
    stations: List<UiStation>,
    onRetry: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (stations.isEmpty()) {
//            Text(
//                text = stringResource(id = R.string.stations_no_stations),
//                modifier = Modifier.align(Alignment.Center),
//                style = MaterialTheme.typography.body1,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colors.onBackground.copy(alpha50),
//            )
            ContentScreenEmpty(modifier = Modifier, onRetry = onRetry)
        } else {
            ContentScreenNotEmpty(stations = stations)
        }
    }
}

@Composable
private fun BoxScope.ContentScreenEmpty(
    modifier:  Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .align(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.stations_no_stations),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 32.dp),
            color = MaterialTheme.colors.onBackground.copy(alpha50),
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

@Composable
private fun ContentScreenNotEmpty(
    stations: List<UiStation>,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(
            items = stations,
            key = { _, item -> item.id }
        ) { index, station ->
            StationView(station)
            if (index != stations.lastIndex) {
                Divider()
            }
        }
    }
}

@Composable
private fun StationView(station: UiStation) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.background)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val (name, address, distance, connectors) = createRefs()

            Text(
                text = station.name,
                modifier = Modifier.constrainAs(name) {
                    start.linkTo(parent.start)
                    end.linkTo(distance.start, margin = 8.dp)
                   width = Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = station.fullAddress.resolve(LocalContext.current),
                modifier = Modifier.constrainAs(address) {
                    top.linkTo(name.bottom)
                    start.linkTo(name.start)
                    end.linkTo(name.end)
                    width = Dimension.fillToConstraints
                },
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = alpha70),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            station.distance?.let {
                Distance(
                    distance = station.distance.resolve(LocalContext.current),
                    modifier = Modifier.constrainAs(distance) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(name.end)
                    }
                )
            }

            station.connectors?.let {
                Connectors(
                    connectors = it,
                    modifier =  Modifier.constrainAs(connectors) {
                        top.linkTo(address.bottom, margin = 16.dp)
                    }
                )
            }
        }
    }
}

@Composable
private fun Distance(
    distance: String,
    modifier: Modifier
) {
    TextButton(
        onClick = {},
        modifier = modifier.wrapContentHeight(),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = distance,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary.copy(alpha = alpha70),
        )

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null
        )
    }
}

private const val CONNECTORS_COUNT = 4

@Composable
private fun Connectors(
    connectors: List<Int>,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        if (connectors.isEmpty()) {
            Text(
                text = stringResource(id = R.string.stations_no_connectors),
                modifier = Modifier.align(Alignment.CenterStart),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = alpha30)

            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(CONNECTORS_COUNT),
                modifier = modifier.fillMaxSize()
            ) {
                items(connectors.take(CONNECTORS_COUNT)) {  kw ->
                    Column {
                        Text(
                            text = kw.toString(),
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary.copy(alpha = alpha80)
                        )
                        Text(
                            text = stringResource(id = R.string.stations_kw),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onBackground.copy(alpha = alpha50)
                        )
                    }
                }
            }
        }
    }
}

// todo. Move to theme
const val alpha80 = 0.8f
const val alpha70 = 0.7f
const val alpha50 = 0.5f
const val alpha30 = 0.3f

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StationsViewPreview() = EvStationsAppTheme {
    val stations = listOf(
        UiStation(
            id = 100,
            name = "Charging Station innogy eMobility Solutions GmbH",
            fullAddress = EvText.PlainText("Recklinghausen, Uferstraße 2-4 la ala lalala dhsd"),
            distance = EvText.PlainText("2.3 km"),
            connectors = listOf(23, 27, 54, 99, 78, 43)
        ),
        UiStation(
            id = 101,
            name = "Charging Station BEW Bergische Energie- und",
            fullAddress = EvText.PlainText("Wipperfürth, Hochstrasse 7"),
            distance = EvText.PlainText("12 km"),
            connectors = listOf()
        )
    )

    ContentScreen(stations, {})
}