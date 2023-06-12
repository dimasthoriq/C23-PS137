import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

suspend fun getAddressFromLatLng(latLng: LatLng): String? = withContext(Dispatchers.IO) {
    val apiKey = "AIzaSyAlAhdAr_iWzpxW_nLFHoB3A7U7b_uFcDI"
    val url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=${latLng.latitude},${latLng.longitude}&key=$apiKey"

    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()

    val response = client.newCall(request).execute()
    val responseBody = response.body?.string()

    if (response.isSuccessful && responseBody != null) {
        val jsonObject = JSONObject(responseBody)
        val results = jsonObject.getJSONArray("results")
        if (results.length() > 0) {
            val address = results.getJSONObject(0).getString("formatted_address")
            address
        } else {
            "Unknown Address"
        }
    } else {
        null
    }
}
