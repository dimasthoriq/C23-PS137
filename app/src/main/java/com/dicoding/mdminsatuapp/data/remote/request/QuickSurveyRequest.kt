package com.dicoding.mdminsatuapp.data.remote.request

import com.google.gson.annotations.SerializedName

data class QuickSurveyRequest(
    @SerializedName("user_id") val userId: String,
    val age: Int,
    @SerializedName("gender") val gender: Int,
    @SerializedName("travel_dist") val travelDist: String,
    val sports: Int,
    val arts: Int,
    val travel: Int,
    val edu: Int
)

