package com.dicoding.mdminsatuapp.data.remote.retrofit

import com.dicoding.mdminsatuapp.data.remote.response.LoginResponse
import com.dicoding.mdminsatuapp.data.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun registerRequest(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginRequest(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>


}
