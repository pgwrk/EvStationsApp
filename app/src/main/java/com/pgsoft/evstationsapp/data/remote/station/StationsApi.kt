package com.pgsoft.evstationsapp.data.remote.station

import retrofit2.http.GET

interface StationsApi {
    @GET("stations")
    suspend fun getStations(): List<StationDto>
}