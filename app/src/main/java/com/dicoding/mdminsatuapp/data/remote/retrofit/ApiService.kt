package com.dicoding.mdminsatuapp.data.remote.retrofit

import com.dicoding.mdminsatuapp.data.remote.input.LoginRequest
import com.dicoding.mdminsatuapp.data.remote.input.RegisterRequest
import com.dicoding.mdminsatuapp.data.remote.response.LoginResponse
import com.dicoding.mdminsatuapp.data.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/register")
    fun registerRequest(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @POST("api/login")
    fun loginRequest(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>


}
