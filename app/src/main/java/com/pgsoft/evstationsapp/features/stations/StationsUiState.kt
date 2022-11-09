package com.pgsoft.evstationsapp.features.stations

import com.pgsoft.evstationsapp.data.common.EvText

sealed class StationsUiState {
    object Loading: StationsUiState()
    class Error(description: EvText): StationsUiState()
    class Content(stations: List<UiStation>): StationsUiState()
}