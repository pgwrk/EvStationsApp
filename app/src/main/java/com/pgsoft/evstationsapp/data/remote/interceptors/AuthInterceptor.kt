package com.pgsoft.evstationsapp.data.remote.interceptors

import com.pgsoft.evstationsapp.data.local.LocalStore
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val localStore: LocalStore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addAuthorizationHeader()
        return chain.proceed(requestBuilder.build())
    }

    private fun Request.Builder.addAuthorizationHeader(): Request.Builder {
        val token = localStore.getToken()
        if (!token.isNullOrBlank()) {
            addHeader(AUTH_HEADER, token)
        }

        return this
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }
}