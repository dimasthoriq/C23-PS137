package com.dicoding.mdminsatuapp.ui.screen.login

import androidx.lifecycle.ViewModel
import com.dicoding.mdminsatuapp.data.local.SessionManager
import com.dicoding.mdminsatuapp.data.remote.input.LoginRequest
import com.dicoding.mdminsatuapp.data.remote.response.LoginResponse
import com.dicoding.mdminsatuapp.data.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    sealed class LoginResult {
        data class Success(val userId: Int, val status: Int) : LoginResult()
        data class Error(val errorMessage: String) : LoginResult()
    }

    fun loginUser(email: String, password: String, onResult: (LoginResult) -> Unit) {
        val login = LoginRequest(email, password)
        apiService.loginRequest(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val userId = response.body()?.userId ?: 0
                    val status = response.body()?.status ?: 0
                    sessionManager.saveUserId(userId.toString())
                    onResult(LoginResult.Success(userId, status))
                } else {
                    val errorMessage = "Failed to login"
                    onResult(LoginResult.Error(errorMessage))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                val errorMessage = t.message ?: "Unknown error occurred"
                onResult(LoginResult.Error(errorMessage))
            }
        })
    }
}
