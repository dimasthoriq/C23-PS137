package com.dicoding.mdminsatuapp.maps

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mdminsatuapp.data.local.PreferenceUtils
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*

private const val TAG = "BasicMapActivity"

val defaultCameraPosition = CameraPosition.fromLatLngZoom(LatLng(1.35, 103.87), 11f)

@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier,
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {},
    locationViewModel: LocationViewModel,
    context: Context,
    coordinates: Pair<Double, Double>? = PreferenceUtils.getCoordinates(context),
    formattedAddress: String? = PreferenceUtils.getLocationName(context)
) {
    val initialCameraPosition = CameraPosition.Builder()
        .target(coordinates?.let { LatLng(it.first, it.second) } ?: defaultCameraPosition.target)
        .zoom(11f)
        .build()

    val locationState = rememberMarkerState(position = initialCameraPosition.target)
    val markerTitle = formattedAddress ?: "Title"

    var circleCenter by remember { mutableStateOf(locationState.position) }
    if (locationState.dragState == DragState.END) {
        circleCenter = locationState.position
    }

    val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
    val ticker by remember { mutableStateOf(0) }
    var mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    var mapVisible by remember { mutableStateOf(true) }

    if (mapVisible) {
        GoogleMap(
            modifier = modifier,
            cameraPositionState = CameraPositionState(initialCameraPosition),
            properties = mapProperties,
            uiSettings = uiSettings,
            onMapLoaded = onMapLoaded,
            onPOIClick = {
                Log.d(TAG, "POI clicked: ${it.name}")
            }
        ) {
            val markerClick: (Marker) -> Boolean = {
                Log.d(TAG, "${it.title} was clicked")
                initialCameraPosition.target.let { target ->
                    Log.d(TAG, "The current projection is: $target")
                }
                false
            }
            MarkerInfoWindowContent(
                state = rememberMarkerState(position = locationState.position),
                onClick = markerClick,
                draggable = true,
            ) {
                Text(markerTitle, color = Color.Red)
            }
            MarkerInfoWindowContent(
                state = rememberMarkerState(position = locationState.position),
                title = "Marker with custom info window.\nZoom in has been tapped $ticker times.",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                onClick = markerClick,
            ) {
                Text(it.title ?: "Title", color = Color.Blue)
            }
            Marker(
                state = rememberMarkerState(
                    position = LatLng(
                        locationViewModel.coordinates.value?.first ?: 0.0,
                        locationViewModel.coordinates.value?.second ?: 0.0
                    )
                ),
                title = "Current Location",
                visible = true
            )

            content()
        }
    }

    Column {
        MapTypeControls(onMapTypeClick = {
            Log.d("GoogleMap", "Selected map type $it")
            mapProperties = mapProperties.copy(mapType = it)
        })
        Row {
            MapButton(
                text = "Reset Map",
                onClick = {
                    mapProperties = mapProperties.copy(mapType = MapType.NORMAL)
                    locationState.position = initialCameraPosition.target
                    locationState.hideInfoWindow()
                }
            )
            MapButton(
                text = "Toggle Map",
                onClick = { mapVisible = !mapVisible },
                modifier = Modifier.testTag("toggleMapVisibility"),
            )
        }
    }
}


@Composable
private fun MapTypeControls(
    onMapTypeClick: (MapType) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(state = ScrollState(0)),
        horizontalArrangement = Arrangement.Center
    ) {
        MapType.values().forEach {
            MapTypeButton(type = it) { onMapTypeClick(it) }
        }
    }
}

@Composable
private fun MapTypeButton(type: MapType, onClick: () -> Unit) =
    MapButton(text = type.toString(), onClick = onClick)

@Composable
private fun MapButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier.padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onPrimary,
            contentColor = Color.Black
        ),
        onClick = onClick
    ) {
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
fun GoogleMapViewPreview() {
    // GoogleMapView(Modifier.fillMaxSize())
}
