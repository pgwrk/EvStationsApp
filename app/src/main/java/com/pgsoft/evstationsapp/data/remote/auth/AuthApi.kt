package com.pgsoft.evstationsapp.data.remote.auth

import retrofit2.http.*

interface AuthApi {

    @FormUrlEncoded
    @POST("auth")
    suspend fun login(@Field("email") userName: String, @Field("code") password: String): AuthDto
}