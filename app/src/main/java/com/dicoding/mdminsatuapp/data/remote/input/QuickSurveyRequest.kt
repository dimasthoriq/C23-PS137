package com.dicoding.mdminsatuapp.data.remote.input

data class QuickSurveyRequest(
    val user_id: String,
    val age: Int,
    val gender: Boolean,
    val travel_dist: String,
    val sports: Int,
    val arts: Int,
    val travel: Int,
    val edu: Int
)
