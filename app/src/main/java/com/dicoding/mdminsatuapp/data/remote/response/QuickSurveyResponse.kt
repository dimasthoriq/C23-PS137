package com.dicoding.mdminsatuapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class QuickSurveyResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String
)
