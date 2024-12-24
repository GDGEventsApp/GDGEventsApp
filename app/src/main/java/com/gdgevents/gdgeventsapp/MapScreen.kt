package com.gdgevents.gdgeventsapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.MotionEvent

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings

import java.util.Locale

private const val TAG = "MapActivity"
@SuppressLint("SuspiciousIndentation")
@Composable
fun MapScreen(location : LatLng) {
    // Camera position and map setup
Log.d("MapsScreen", "MapScreen: $location")
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 17f) // Your predefined camera position
    }
    var selectedLocation =  location
    val locationText = remember { mutableStateOf<String>("") }

        Column(modifier = Modifier.fillMaxSize()) {
            // Map display with GoogleMapViewInColumn
            GoogleMapViewInColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp), // Height of the map
                cameraPositionState = cameraPositionState,

                onMapLoaded = {
                    // Map loaded callback
                    Log.d(TAG, "Map Loaded")
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 17f)

                },


                onLocationSelected = { location ->
                    // Callback when a location is selected
                    selectedLocation = location
                    Log.d(TAG, "Location selected: $location")
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 17f)
                }
            )
            locationText.value = getCityAndCountryFromCoordinates(context = LocalContext.current, latLng = selectedLocation)
                 Log.d("Location At MapsScreen", "MapScreen: $locationText")
            // Spacer to add space between the map and checkbox
            Spacer(modifier = Modifier.height(16.dp))

            // CheckBoxDone and Button
            CheckBoxDone(modifier = Modifier.fillMaxWidth(),locationText.value)
        }
}
@Composable
fun CheckBoxDone(modifier: Modifier,selectedLocation: String?) {
    Log.d(TAG, "CheckBoxDone: $selectedLocation")
    Column(
        modifier = modifier
            .padding(16.dp) // Add padding for better spacing
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = selectedLocation ?: "No location selected", // Show a default text if no location is selected
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.labelSmall
        )


        Button(
            onClick = {
                // TODO: Handle button click
            },
            modifier = Modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(Color.Blue),
            enabled = !selectedLocation.isNullOrEmpty() // Button is enabled only if checked is true
        ) {
            Text(text = "Done", color = Color.White)
        }
    }
}

fun getCityAndCountryFromCoordinates(context: Context, latLng: LatLng?): String {
    if (latLng == null) {
        return "Location not found"
    }
    val geocoder = Geocoder(context, Locale.getDefault())

    // Attempt to get addresses from the coordinates
    return try {
        val addresses: List<Address>? = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 10)

        // Check if addresses are available
        if (addresses.isNullOrEmpty()) {
            "Location not found"
        } else {
            val address = addresses[0]
            val city = address.subLocality ?:address.adminArea ?:address.subAdminArea ?:"Unknown city"
            val country = address.countryName ?: "Unknown country"
            "$city, $country"
        }
    } catch (e: Exception) {
        // Handle exceptions (e.g., network issues)
        "Error retrieving location"
    }
}


@Composable
fun GoogleMapViewInColumn(
    modifier: Modifier,
    cameraPositionState: CameraPositionState,
    onMapLoaded: () -> Unit,
    onLocationSelected: (LatLng) -> Unit
) {
    val gizaState = rememberMarkerState(position = LatLng(29.978428, 31.139099))

    val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = uiSettings,
        onMapLoaded = onMapLoaded,
        onMapClick = { latLng ->
            // When the user taps the map, update the selected position
            onLocationSelected(latLng)
        }
    ) {
        MarkerInfoWindowContent(
            state = gizaState, // The position of the marker (Singapore in this case)
            title = "Giza", // The title for the marker
            snippet = "A marker for ", // A brief description for the marker
            onClick = { marker ->
                // Handle marker click event here
                Log.d(TAG, "Marker clicked: ${marker.title}")
                false // Return false to disable the default info window
            }
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MapInColumn(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    columnScrollingEnabled: Boolean,
    onMapTouched: () -> Unit,
    onMapLoaded: () -> Unit,
) {
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        var isMapLoaded by remember { mutableStateOf(false) }

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(
                    rememberScrollState(),
                    columnScrollingEnabled
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            for (i in 1..20) {
                Text(
                    text = "Item $i",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .testTag("Item $i")
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Column (
                Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Increased height for the map
            ) {
                GoogleMapViewInColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // Fixed height for the map
                        .testTag("Map")
                        .pointerInteropFilter(
                            onTouchEvent = {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        onMapTouched()
                                        false
                                    }

                                    else -> {
                                        Log.d(
                                            TAG,
                                            "MotionEvent ${it.action} - this never triggers."
                                        )
                                        true
                                    }
                                }
                            }
                        ),
                    cameraPositionState = cameraPositionState,
                    onMapLoaded = {
                        isMapLoaded = true
                        onMapLoaded()
                    },
                    onLocationSelected = {location ->
                        selectedLocation = location
                        Log.d(TAG, "Location selected: $location")
                    }
                )
                if (!isMapLoaded) {
                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier
                            .fillMaxWidth(),
                        visible = !isMapLoaded,
                        enter = EnterTransition.None,
                        exit = fadeOut()
                    ) {

                    }
                }
            }

        }

    }



}
@Composable
@Preview
fun GoogleMapViewPreview() {
    GDGEventsAppTheme  {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(29.978428, 31.139099), 17f)
        }

        MapInColumn(
            modifier = Modifier.fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            columnScrollingEnabled = true,
            onMapTouched = {}
        ) {
            // Additional actions after the map is loaded
        }
    }
}

