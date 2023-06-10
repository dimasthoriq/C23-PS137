package com.dicoding.mdminsatuapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String
)
