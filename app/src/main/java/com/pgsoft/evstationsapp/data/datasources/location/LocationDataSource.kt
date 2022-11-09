package com.pgsoft.evstationsapp.data.datasources.location

import android.location.Location

interface LocationDataSource {
    fun getLocation(): Location
}