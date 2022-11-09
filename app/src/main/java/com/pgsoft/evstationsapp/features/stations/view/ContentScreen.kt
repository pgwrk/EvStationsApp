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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.features.stations.UiStation
import com.pgsoft.evstationsapp.ui.theme.EvStationsAppTheme

@Composable
fun ContentScreen(
    stations: List<UiStation>,
    modifier: Modifier
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
        ) {
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
                text = stringResource(
                    id = R.string.stations_address,
                    station.address,
                    station.city
                ),
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

            station.distanceKm?.let {
                Distance(
                    distance = station.distanceKm,
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
    distance: Float,
    modifier: Modifier
) {
    TextButton(
        onClick = {},
        modifier = modifier.wrapContentHeight(),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = stringResource(id = R.string.stations_distance, distance),
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
    LazyVerticalGrid(
        columns = GridCells.Fixed(CONNECTORS_COUNT),
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        userScrollEnabled = false
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

// todo. Move to theme
const val alpha80 = 0.8f
const val alpha70 = 0.7f
const val alpha50 = 0.5f

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun StationsViewPreview() = EvStationsAppTheme {
    val stations = listOf(
        UiStation(
            id = 100,
            name = "Charging Station innogy eMobility Solutions GmbH",
            address = "Uferstraße 2-4 la ala lalala dhsd",
            city = "Recklinghausen",
            distanceKm = 2.87392f,
            connectors = listOf(23, 27, 54, 108, 78, 43)
        ),
        UiStation(
            id = 101,
            name = "Charging Station BEW Bergische Energie- und",
            address = "Hochstrasse 7",
            city = "Wipperfürth",
            distanceKm = 5.3497f,
            connectors = listOf(22, 44, 33)
        )
    )

    ContentScreen(stations, modifier = Modifier.fillMaxSize())
}