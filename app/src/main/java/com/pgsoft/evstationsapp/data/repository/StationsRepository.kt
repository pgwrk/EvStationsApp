package com.pgsoft.evstationsapp.data.repository

import com.pgsoft.evstationsapp.data.model.Station
import com.pgsoft.evstationsapp.data.remote.station.StationsApi
import com.pgsoft.evstationsapp.data.remote.station.toDomain
import com.pgsoft.evstationsapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationsRepository @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val stationsApi: StationsApi
): BaseRepository(ioDispatcher) {

    suspend fun getStations(): Result<List<Station>> {
        return makeApiCall {
            val response = stationsApi.getStations()
            response.map { it.toDomain() }
        }
    }
}