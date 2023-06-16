package com.dicoding.mdminsatuapp.data.remote.request

data class QuickSurveyRequest(
    val userId: String,
    val age: Int,
    val gender: Boolean,
    val travelDist: String,
    val sports: Int,
    val arts: Int,
    val travel: Int,
    val edu: Int
)
