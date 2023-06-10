package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.dummy.arts
import com.dicoding.mdminsatuapp.dummy.edu
import com.dicoding.mdminsatuapp.dummy.sports
import com.dicoding.mdminsatuapp.dummy.travel
import com.dicoding.mdminsatuapp.maps.LocationViewModel
import com.dicoding.mdminsatuapp.navigation.Screen
import com.dicoding.mdminsatuapp.ui.components.PrimaryButton
import com.dicoding.mdminsatuapp.ui.components.SurveyChipsGroup
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import java.util.*


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QuickSurveyInterestScreen(navController: NavController) {
    val locationViewModel: LocationViewModel = viewModel()
    var locationPermissionGranted by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val formattedAddress by locationViewModel.formattedAddress.collectAsState()
    LaunchedEffect(formattedAddress) {
        if (formattedAddress != null) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.QuickSurveyInterest.route) { inclusive = true }
            }
        }
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        state = rememberLazyListState()
    ) {
        item {
            Text(
                text = "Quick Survey",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Choose 3 or more activities you might be interested in!")
        }

        item { SurveyChipsGroup("Sports", sports) }
        item { SurveyChipsGroup("Arts", arts) }
        item { SurveyChipsGroup("Travel", travel) }
        item { SurveyChipsGroup("Edu", edu) }

        item {
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                PrimaryButton(
                    text = "Done",
                    onClick = {
                        if (permissionState.hasPermission) {
                            locationPermissionGranted = true
                            getCurrentLocation(
                                context,
                                navController,
                                permissionState,
                                locationViewModel
                            )
                        } else {
                            if (permissionState.permissionRequested) {
                                // Handle the case where permission was previously requested but denied by the user
                                // You can show a dialog or display a message to the user explaining why the permission is required
                                // and prompt them to grant the permission from system settings.
                            } else {
                                // Request the permission
                                permissionState.launchPermissionRequest()
                            }

                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
private fun getCurrentLocation(
    context: Context,
    navController: NavController,
    permissionState: PermissionState,
    locationViewModel: LocationViewModel
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
                            Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun QuickSurveyInterestPreview() {
    val navController = rememberNavController()

    QuickSurveyInterestScreen(navController = navController)
}