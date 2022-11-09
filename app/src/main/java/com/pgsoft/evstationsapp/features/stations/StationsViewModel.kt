package com.pgsoft.evstationsapp.features.stations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.data.datasources.location.LocationDataSource
import com.pgsoft.evstationsapp.data.repository.StationsRepository
import com.pgsoft.evstationsapp.extension.asEvTextOnError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val stationsRepository: StationsRepository
): ViewModel() {

    // must be moved to OptionsRepository when implemented
    private val showDistanceOption: Boolean = true
    private val showConnectorsOption: Boolean = true

    private val _uiState: MutableStateFlow<StationsUiState> = MutableStateFlow(StationsUiState.Loading)
    val uiState : StateFlow<StationsUiState> = _uiState

    init {
        load()
    }

    fun load() {
        setState(StationsUiState.Loading)
        viewModelScope.launch {
            val response = stationsRepository.getStations()
            if (response.isFailure) {
                setState(StationsUiState.Error(description = response.asEvTextOnError(R.string.login_common_error)))
            } else {
                val stations = response.getOrNull()?.let { stations ->
                    val userLocation = locationDataSource.getLocation()
                    val mapper = StationToUiStationMapper()

                    stations
                        .sortedBy {
                            userLocation.distanceTo(it.location)
                        }
                        .map { mapper.map(it, userLocation, showDistanceOption, showConnectorsOption) }
                } ?: run {
                    listOf()
                }

                setState(StationsUiState.Content(stations = stations))
            }
        }
    }

    private fun setState(newState: StationsUiState) {
        _uiState.value = newState
    }
}