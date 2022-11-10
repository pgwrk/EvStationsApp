package com.pgsoft.evstationsapp.features.stations.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgsoft.evstationsapp.data.model.StationsSettings
import com.pgsoft.evstationsapp.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel() {

    val uiState: StateFlow<StationsSettings> = settingsRepository.stationsSettingsFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = StationsSettings()
        )

    fun setShowDistance(show: Boolean) {
        settingsRepository.setStationsSettings(uiState.value.copy(showDistance = show))
    }

    fun setShowConnectors(show: Boolean) {
        settingsRepository.setStationsSettings(uiState.value.copy(showConnectors = show))
    }
}