package com.dicoding.mdminsatuapp.data.remote.retrofit

import com.dicoding.mdminsatuapp.data.remote.request.LoginRequest
import com.dicoding.mdminsatuapp.data.remote.request.QuickSurveyRequest
import com.dicoding.mdminsatuapp.data.remote.request.RegisterRequest
import com.dicoding.mdminsatuapp.data.remote.response.LoginResponse
import com.dicoding.mdminsatuapp.data.remote.response.QuickSurveyResponse
import com.dicoding.mdminsatuapp.data.remote.response.RegisterResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/register")
    fun registerRequest(
        @Body registerRequest: RegisterRequest
    ): Call<ResponseBody>

    @POST("api/login")
    fun loginRequest(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("api/quickSurvey")
    fun quickSurveyRequest(
        @Body quickSurveyRequest: QuickSurveyRequest
    ): Call<QuickSurveyResponse>
}
