package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.dicoding.mdminsatuapp.data.remote.request.QuickSurveyRequest
import com.dicoding.mdminsatuapp.data.remote.response.QuickSurveyResponse
import com.dicoding.mdminsatuapp.data.remote.retrofit.ApiConfig
import com.dicoding.mdminsatuapp.ui.components.ChipData
import com.dicoding.mdminsatuapp.ui.components.SurveyChipData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuickSurveyViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    var age by mutableStateOf(0)
    var gender by mutableStateOf(0)
    var distance by mutableStateOf("")

    val selectedSports = mutableStateListOf<SurveyChipData>()
    val selectedArts = mutableStateListOf<SurveyChipData>()
    val selectedTravel = mutableStateListOf<SurveyChipData>()
    val selectedEdu = mutableStateListOf<SurveyChipData>()

    fun handleChipSelection(chip: SurveyChipData, selectedChips: MutableList<SurveyChipData>) {
        chip.selected.value = !chip.selected.value
        if (chip.selected.value) {
            selectedChips.add(chip)
        } else {
            selectedChips.remove(chip)
        }
    }

    fun uploadQuickSurveyData(
        userId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val sportsCount = selectedSports.count { it.selected.value }
        val artsCount = selectedArts.count { it.selected.value }
        val travelCount = selectedTravel.count { it.selected.value }
        val eduCount = selectedEdu.count { it.selected.value }

        val request = QuickSurveyRequest(
            userId = userId,
            age = age,
            gender = gender,
            travelDist = distance,
            sports = sportsCount,
            arts = artsCount,
            travel = travelCount,
            edu = eduCount
        )

        apiService.quickSurveyRequest(request).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    val responseText = response.body()?.string()
                    if (responseText == "OK") {
                        onSuccess.invoke()
                    } else {
                        onError.invoke("Upload failed")
                    }
                } else {
                    onError.invoke("Upload failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onError.invoke("Upload failed: ${t.message}")
            }
        })
    }


}