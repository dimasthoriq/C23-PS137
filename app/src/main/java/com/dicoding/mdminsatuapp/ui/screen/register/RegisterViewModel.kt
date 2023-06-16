package com.dicoding.mdminsatuapp.ui.screen.register

import androidx.lifecycle.ViewModel
import com.dicoding.mdminsatuapp.data.remote.request.RegisterRequest
import com.dicoding.mdminsatuapp.data.remote.response.RegisterResponse
import com.dicoding.mdminsatuapp.data.remote.retrofit.ApiConfig
import okhttp3.ResponseBody
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
        apiService.registerRequest(register).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.body()?.string() == "OK") {
                    onSuccess()
                } else {
                    if (response.code() == 401 && response.errorBody()?.string() == "Unauthorized") {
                        onError("User already exists")
                    } else {
                        onError("Failed to register")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onError(t.message ?: "Unknown error occurred")
            }
        })
    }


}
