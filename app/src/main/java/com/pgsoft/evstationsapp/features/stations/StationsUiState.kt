package com.pgsoft.evstationsapp.features.stations

import com.pgsoft.evstationsapp.data.common.EvText

sealed class StationsUiState {
    object Loading: StationsUiState()
    class Error(val description: EvText): StationsUiState()
    class Content(val stations: List<UiStation>): StationsUiState()
}