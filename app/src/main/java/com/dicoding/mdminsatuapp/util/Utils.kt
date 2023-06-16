import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.maps.LocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalPermissionsApi::class)
fun getCurrentLocation(
    context: Context,
    navController: NavController,
    permissionState: PermissionState,
    locationViewModel: LocationViewModel,
) {
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    if (permissionState.hasPermission) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        if (location != null) {
                            val latitude = location.latitude
                            val longitude = location.longitude
                            Log.d("Location", "Latitude: $latitude, Longitude: $longitude")

                            locationViewModel.setCoordinates(latitude, longitude)
                            locationViewModel.getFormattedAddress(context, latitude, longitude)


                        } else {
                            Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        // Failure listener logic
                    }
            }
        }
    }
}