package com.pgsoft.evstationsapp.extension

import android.location.Location

fun Location.distanceToInKm(latitude: Double, longitude: Double): Float {
    val destPoint = Location("").apply {
        this.latitude = latitude
        this.longitude = longitude
    }

    return distanceTo(destPoint) / 1000
}