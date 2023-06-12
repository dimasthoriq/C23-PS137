package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
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
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.dummy.arts
import com.dicoding.mdminsatuapp.dummy.edu
import com.dicoding.mdminsatuapp.dummy.sports
import com.dicoding.mdminsatuapp.dummy.travel
import com.dicoding.mdminsatuapp.maps.LocationViewModel
import com.dicoding.mdminsatuapp.navigation.Screen
import com.dicoding.mdminsatuapp.ui.components.ChipData
import com.dicoding.mdminsatuapp.ui.components.PrimaryButton
import com.dicoding.mdminsatuapp.ui.components.SurveyChipsGroup
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import java.util.*
import kotlin.system.exitProcess

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
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

    val selectedChips = remember { mutableStateListOf<ChipData>() }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
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

            item { SurveyChipsGroup("Sports", sports, selectedChips) }
            item { SurveyChipsGroup("Arts", arts, selectedChips) }
            item { SurveyChipsGroup("Travel", travel, selectedChips) }
            item { SurveyChipsGroup("Edu", edu, selectedChips) }

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
                                if (selectedChips.size >= 3) {
                                    locationPermissionGranted = true
                                    getCurrentLocation(
                                        context,
                                        navController,
                                        permissionState,
                                        locationViewModel
                                    )
                                } else {
                                    Toast.makeText(
                                        context,
                                        R.string.error_select_chips,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                if (permissionState.permissionRequested) {
                                    Toast.makeText(
                                        context,
                                        "Location permission is required. Please grant the permission to continue.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    exitProcess(0)
                                } else {
                                    // Request the permission
                                    permissionState.launchPermissionRequest()
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = selectedChips.size >= 3,
                    )
                }
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
