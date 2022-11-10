package com.pgsoft.evstationsapp.data.repository

import com.pgsoft.evstationsapp.data.local.LocalStore
import com.pgsoft.evstationsapp.data.local.SettingName
import com.pgsoft.evstationsapp.data.model.StationsSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val localStore: LocalStore
) {
    private val _stationsSettingsFlow = MutableStateFlow(StationsSettings())
    val stationsSettingsFlow: StateFlow<StationsSettings> = _stationsSettingsFlow

    init {
        val settings = StationsSettings(
            showDistance = localStore.getSetting(SettingName.ShowDistance, true),
            showConnectors = localStore.getSetting(SettingName.ShowConnectors, true)
        )

        _stationsSettingsFlow.value = settings
    }

    fun setStationsSettings(settings: StationsSettings) {
        localStore.saveSetting(SettingName.ShowConnectors, settings.showConnectors)
        localStore.saveSetting(SettingName.ShowDistance, settings.showDistance)

        _stationsSettingsFlow.value = settings
    }
}