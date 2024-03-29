package com.pgsoft.evstationsapp.features.stations.stationslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgsoft.evstationsapp.R
import com.pgsoft.evstationsapp.data.datasources.location.LocationDataSource
import com.pgsoft.evstationsapp.data.model.Station
import com.pgsoft.evstationsapp.data.repository.SettingsRepository
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
    private val stationsRepository: StationsRepository,
    settingsRepository: SettingsRepository
): ViewModel() {

    private var stations: List<Station> = listOf()
    private val settingsState = settingsRepository.stationsSettingsFlow

    private val _uiState: MutableStateFlow<StationsUiState> = MutableStateFlow(StationsUiState.Loading)
    val uiState : StateFlow<StationsUiState> = _uiState

    init {
        load()
        viewModelScope.launch {
           settingsState.collect()  {
                if (uiState.value is StationsUiState.Content) {
                    combineStationsWithSetting()
                }
            }
        }
    }

    fun load() {
        setState(StationsUiState.Loading)
        viewModelScope.launch {
            val response = stationsRepository.getStations()
            if (response.isFailure) {
                setState(StationsUiState.Error(description = response.asEvTextOnError(R.string.login_common_error)))
            } else {
                response.getOrNull()?.let { stations ->
                    this@StationsViewModel.stations = stations
                    combineStationsWithSetting()
                } ?: run {
                    setState(StationsUiState.Content(stations = listOf()))
                }
            }
        }
    }

    private fun combineStationsWithSetting() {
        val userLocation = locationDataSource.getLocation()
        val mapper = StationToUiStationMapper()

        val uiStations = stations
            .sortedBy { station -> userLocation.distanceTo(station.location) }
            .map { station ->
                mapper.map(
                    station,
                    userLocation,
                    settingsState.value.showDistance,
                    settingsState.value.showConnectors
                )
            }

        setState(StationsUiState.Content(stations = uiStations))
    }

    private fun setState(newState: StationsUiState) {
        _uiState.value = newState
    }
}