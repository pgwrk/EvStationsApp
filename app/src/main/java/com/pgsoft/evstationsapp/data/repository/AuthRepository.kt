package com.pgsoft.evstationsapp.data.repository

import com.pgsoft.evstationsapp.data.local.LocalStore
import com.pgsoft.evstationsapp.data.remote.auth.AuthApi
import com.pgsoft.evstationsapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authApi: AuthApi,
    private val localStore: LocalStore
): BaseRepository(ioDispatcher) {

    suspend fun login(userName: String, password: String): Result<String> {
        return makeApiCall {
            val response = authApi.login(userName, password)
            saveToken(response.token)
            response.token ?: ""
        }
    }

    fun getToken(): String? = localStore.getToken()

    private fun saveToken(token: String?) {
        token?.let {
            localStore.saveToken(token)
        }
    }
}