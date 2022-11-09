package com.pgsoft.evstationsapp.data.model

class Station(
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val connectors: List<Int>
)