package com.pgsoft.evstationsapp.features.stations

import android.location.Location
import com.pgsoft.evstationsapp.data.model.Station
import com.pgsoft.evstationsapp.extension.distanceToInKm

class UiStation(
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val distanceKm: Float?,
    val connectors: List<Int>?
)

fun Station.toUiStation(
    currentUserPosition: Location,
    showDistance: Boolean,
    showConnectors: Boolean
): UiStation =
    UiStation(
        id = id,
        name = name,
        address = address,
        city = city,
        distanceKm = if (showDistance) currentUserPosition.distanceToInKm(latitude, longitude) else null,
        connectors = if (showConnectors) connectors else null
    )