package com.dicoding.mdminsatuapp.ui.screen.register

import androidx.lifecycle.ViewModel
import com.dicoding.mdminsatuapp.data.remote.input.RegisterRequest
import com.dicoding.mdminsatuapp.data.remote.response.RegisterResponse
import com.dicoding.mdminsatuapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val register = RegisterRequest(name, email, password)
        apiService.registerRequest(register).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse?.code == "200") {
                        onSuccess()
                    } else {
                        onError(registerResponse?.message ?: "Failed to register")
                    }
                } else {
                    onError("Failed to register")
                }
            }


            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                onError(t.message ?: "Unknown error occurred")
            }

        })
    }

}
