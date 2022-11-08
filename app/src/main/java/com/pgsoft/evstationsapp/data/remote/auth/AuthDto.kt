package com.pgsoft.evstationsapp.data.remote.auth

import com.google.gson.annotations.SerializedName

class AuthDto(
    @SerializedName("message")
    val errorMessage: String?,
    @SerializedName("token")
    val token: String?
)