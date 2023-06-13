package com.dicoding.mdminsatuapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("status")
    val status: Int?
)

