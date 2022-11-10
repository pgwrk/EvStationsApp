package com.pgsoft.evstationsapp.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

enum class SettingName(val value: String) {
    ShowDistance("ShowDistance"),
    ShowConnectors("ShowConnectors"),
    Token("AppToken")
}

@Singleton
class LocalStore @Inject constructor(@ApplicationContext context: Context) {

    // The better way is to use EncryptedSharedPreferences
    private val prefs: SharedPreferences = context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit()?.putString(SettingName.Token.value, token)?.apply()
    }

    fun getToken(): String? = prefs.getString(SettingName.Token.value, null)

    fun saveSetting(settingName: SettingName, value: Boolean) {
        prefs.edit()?.putBoolean(settingName.value, value)?.apply()
    }

    fun getSetting(settingName: SettingName, defaultValue: Boolean) =
        prefs.getBoolean(settingName.value, defaultValue)
}

private const val STORE_NAME = "app data"