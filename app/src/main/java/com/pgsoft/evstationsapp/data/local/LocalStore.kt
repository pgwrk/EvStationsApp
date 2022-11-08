package com.pgsoft.evstationsapp.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStore @Inject constructor(@ApplicationContext context: Context) {

    // The better way is to use EncryptedSharedPreferences
    private val prefs: SharedPreferences = context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit()?.putString(TOKEN, token)?.apply()
    }

    fun getToken(): String? = prefs.getString(TOKEN, null)
}

private const val STORE_NAME = "app data"
private const val TOKEN = "app_token"