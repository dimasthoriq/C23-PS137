import retrofit2.http.GET
import retrofit2.http.Query

data class GeocodingResponse(
    val results: List<Result>,
    val status: String
) {
    data class Result(
        val formatted_address: String,
        val address_components: List<AddressComponent>
    ) {
        data class AddressComponent(
            val long_name: String,
            val short_name: String,
            val types: List<String>
        )
    }
}

interface GeocodingService {
    @GET("maps/api/geocode/json")
    suspend fun reverseGeocode(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String
    ): GeocodingResponse
}


