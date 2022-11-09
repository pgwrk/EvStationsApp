package com.pgsoft.evstationsapp.data.datasources.location

import android.location.Location
import javax.inject.Inject

class LocationDataSourceFake @Inject constructor(): LocationDataSource {
    override fun getLocation(): Location {
        // Return coordinates for the Helsinki Cathedral
        return Location("Current position").apply {
            latitude = 60.171445715844094
            longitude = 24.952568008535746
        }
    }
}