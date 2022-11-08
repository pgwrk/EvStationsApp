package com.pgsoft.evstationsapp.data.repository

import com.google.gson.GsonBuilder
import com.pgsoft.evstationsapp.data.remote.auth.AuthDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

open class BaseRepository(private val ioDispatcher: CoroutineDispatcher) {

    protected suspend fun<T> makeApiCall(call: suspend () -> T): Result<T> =
        try {
            val data = withContext(ioDispatcher) {
                call()
            }
            Result.success(data)
        } catch (e: HttpException) {
            val json = e.response()?.errorBody()?.string() ?: ""
            val gson = GsonBuilder().create()
            val dto = gson.fromJson(json, AuthDto::class.java)
            Result.failure(Exception(dto.errorMessage))
        } catch (e: Exception) {
            Result.failure(e)
        }
}