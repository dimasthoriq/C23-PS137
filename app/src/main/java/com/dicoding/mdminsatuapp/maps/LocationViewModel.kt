package com.dicoding.mdminsatuapp.maps

import GeocodingService
import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class LocationViewModel : ViewModel() {
    val coordinates = mutableStateOf<Pair<Double, Double>?>(null)

    private val _formattedAddress = MutableStateFlow<String?>(null)
    val formattedAddress: StateFlow<String?> = _formattedAddress



    fun setCoordinates(latitude: Double, longitude: Double) {
        coordinates.value = Pair(latitude, longitude)
    }

    fun setFormattedAddress(address: String?) {
        _formattedAddress.value = address
    }

    fun getFormattedAddress(context: Context, latitude: Double, longitude: Double) {
        val geocoder = Geocoder(context)
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val formattedAddress = addresses[0].getAddressLine(0)
                _formattedAddress.value = formattedAddress
                Log.d("Location", "Formatted Address: $formattedAddress")

            }
        } catch (e: IOException) {
            Log.e("LocationViewModel", "Failed to get formatted address: ${e.message}")
        }
    }




}
