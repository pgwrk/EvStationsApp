package com.pgsoft.evstationsapp.features.stations

import android.location.Location
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.data.common.EvText
import com.pgsoft.evstationsapp.data.model.Station

class UiStation(
    val id: Long,
    val name: String,
    val fullAddress: EvText,
    val distance: EvText?,
    val connectors: List<Int>?
)

class StationToUiStationMapper {
    fun map(station: Station,
            currentUserPosition: Location,
            showDistance: Boolean,
            showConnectors: Boolean
    ): UiStation = with(station) {

        val distanceInKm = currentUserPosition.distanceTo(station.location) / 1000

        UiStation(
            id = id,
            name = name,
            fullAddress = getFullAddress(address, city),
            distance = if (showDistance) getDistanceAsText(distanceInKm) else null,
            connectors = if (showConnectors) connectors else null
        )
    }

    private fun getFullAddress(address: String, city: String): EvText =
        when {
            address.isNotBlank() && city.isNotBlank() -> EvText.ResText(R.string.stations_address, listOf(city, address))
            address.isNotBlank() && city.isBlank() -> EvText.PlainText(address)
            address.isBlank() && city.isNotBlank() -> EvText.PlainText(city)
            else -> EvText.ResText(R.string.stations_no_address)
        }

    private fun getDistanceAsText(distance: Float): EvText =
        if (distance < SMALL_DISTANCE_KM)
            EvText.ResText(R.string.stations_small_distance, listOf(distance))
        else
            EvText.ResText(R.string.stations_big_distance, listOf(distance.toInt()))

    companion object {
        private const val SMALL_DISTANCE_KM = 10
    }
}