package com.pgsoft.evstationsapp.data.model

import android.location.Location

class Station(
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val location: Location,
    val connectors: List<Int>
)