package com.pgsoft.evstationsapp.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.asResponseBody
import okhttp3.mockwebserver.MockResponse
import java.io.FileNotFoundException

class StationApiMockInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()

        // mock "/stations" api call
        val response: Response = if (uri.endsWith("/stations")) {
            chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .body(getBody())
                .addHeader("content-type", "application/json")
                .build()
        } else {
            chain.proceed(chain.request())
        }

        return response
    }
}

private fun getBody(): ResponseBody? =
    MockResponse()
        .setBodyFromFile("assets/stations_mock.json")
        .getBody()?.asResponseBody("application/json".toMediaTypeOrNull())

fun MockResponse.setBodyFromFile(fileName: String): MockResponse {
    val inputStream = this::class.java.classLoader?.getResourceAsStream(fileName)
        ?: throw FileNotFoundException("File [$fileName] not found.")

    val text = inputStream.bufferedReader().readText()

    setBody(text)
    return this
}