package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.dicoding.mdminsatuapp.data.remote.request.QuickSurveyRequest
import com.dicoding.mdminsatuapp.data.remote.response.QuickSurveyResponse
import com.dicoding.mdminsatuapp.data.remote.retrofit.ApiConfig
import com.dicoding.mdminsatuapp.ui.components.ChipData
import com.dicoding.mdminsatuapp.ui.components.SurveyChipData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuickSurveyViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()

    var age by mutableStateOf(0)
    var gender by mutableStateOf(true)
    var distance by mutableStateOf("")

     val selectedSports = mutableStateListOf<ChipData>()
     val selectedArts = mutableStateListOf<ChipData>()
     val selectedTravel = mutableStateListOf<ChipData>()
     val selectedEdu = mutableStateListOf<ChipData>()

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

        apiService.quickSurveyRequest(request).enqueue(object : Callback<QuickSurveyResponse> {
            override fun onResponse(
                call: Call<QuickSurveyResponse>,
                response: Response<QuickSurveyResponse>
            ) {
                if (response.isSuccessful) {
                    val surveyResponse = response.body()
                    if (surveyResponse?.code == "200") {
                        onSuccess.invoke()
                    } else {
                        onError.invoke("Upload failed: ${surveyResponse?.message}")
                    }
                } else {
                    onError.invoke("Upload failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<QuickSurveyResponse>, t: Throwable) {
                onError.invoke("Upload failed: ${t.message}")
            }
        })
    }

}
