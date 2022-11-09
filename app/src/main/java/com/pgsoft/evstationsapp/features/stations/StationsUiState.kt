package com.pgsoft.evstationsapp.features.stations

import com.pgsoft.evstationsapp.data.model.Station

sealed class StationsUiState {
    object Loading: StationsUiState()
    class Error(description: String): StationsUiState()
    class Content(stations: List<Station>): StationsUiState()
}