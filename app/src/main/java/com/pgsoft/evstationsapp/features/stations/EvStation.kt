package com.pgsoft.evstationsapp.features.stations

class EvStation(
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val distanceKm: Float?,
    val connectors: List<Int>?
)