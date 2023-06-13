package com.dicoding.mdminsatuapp.ui.screen.login

import androidx.lifecycle.ViewModel
import com.dicoding.mdminsatuapp.data.remote.input.LoginRequest
import com.dicoding.mdminsatuapp.data.remote.response.LoginResponse
import com.dicoding.mdminsatuapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val login = LoginRequest(email, password)
        apiService.loginRequest(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Failed to login")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError(t.message ?: "Unknown error occurred")
            }
        })
    }
}