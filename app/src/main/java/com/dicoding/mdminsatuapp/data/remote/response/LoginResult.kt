package com.dicoding.mdminsatuapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResult(

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("status")
    val status: String
)
