package com.pgsoft.evstationsapp.features.stations

import com.pgsoft.evstationsapp.data.common.EvText
import com.pgsoft.evstationsapp.data.model.Station

sealed class StationsUiState {
    object Loading: StationsUiState()
    class Error(description: EvText): StationsUiState()
    class Content(stations: List<Station>): StationsUiState()
}