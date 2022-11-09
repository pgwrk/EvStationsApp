package com.pgsoft.evstationsapp.data.remote.station

import android.location.Location
import com.google.gson.annotations.SerializedName
import com.pgsoft.evstationsapp.data.model.Station

class StationDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("evses")
    val evses: List<EvseDto>
)

class EvseDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("groupName")
    val groupName: String,
    @SerializedName("connectors")
    val connectors: List<ConnectorDto>
)

class ConnectorDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("maxKw")
    val maxKw: Float
)

fun StationDto.toDomain(): Station {
    val connectors = evses
        .flatMap { evse -> evse.connectors.map { it.maxKw } }
        .filter { maxKw -> maxKw > 0 }

    val res = Station(
        id = id,
        name,
        address = address,
        city = city,
        location = Location("").apply {
            latitude = this@toDomain.latitude
            longitude = this@toDomain.longitude
        },
        connectors = connectors.map { it.toInt() }
    )

    return res
}