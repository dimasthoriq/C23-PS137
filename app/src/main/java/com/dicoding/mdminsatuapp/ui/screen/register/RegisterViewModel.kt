package com.dicoding.mdminsatuapp.ui.screen.register

import com.dicoding.mdminsatuapp.data.remote.ApiService
import com.dicoding.mdminsatuapp.data.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val apiService: ApiService) {

    fun register(name: String, email: String, password: String) {
        val registerCall = apiService.registerRequest(name, email, password)
        registerCall.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    // Tangani response berhasil di sini
                    val registerResponse = response.body()
                    // Lakukan sesuatu dengan data response yang diterima
                } else {
                    // Tangani response gagal di sini
                    // Misalnya, menampilkan pesan error kepada pengguna
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                // Tangani kegagalan request di sini
                // Misalnya, menampilkan pesan error kepada pengguna
            }
        })


    }

}