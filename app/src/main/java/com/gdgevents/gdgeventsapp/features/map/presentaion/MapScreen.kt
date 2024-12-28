package com.gdgevents.gdgeventsapp.features.map.presentaion

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.Locale

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = true) }
    val state by viewModel.state.collectAsState()
    val dynamicText by viewModel.dynamicText.collectAsState()
    val searchQuery = remember { mutableStateOf("") }


    val hasLocationPermission = remember {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                viewModel.fetchUserLocation(context)
            } else {
                Log.e("MapScreen", "Location permission denied")
            }
        }
    )


    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            viewModel.loadSavedLocation() // Load saved location on screen start
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        val cameraPositionState = rememberCameraPositionState()

        LaunchedEffect(state.marker) {
            state.marker?.let { marker ->
                val savedLatLng = LatLng(marker.latitude, marker.longitude)
                cameraPositionState.position = CameraPosition.fromLatLngZoom(savedLatLng, 15f)
            }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = state.properties,
            uiSettings = uiSettings,
            onMapClick = { latLng ->
                if (!hasLocationPermission) {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                } else {
                    viewModel.addMarker(latLng, context)
                    reverseGeocodeAsync(context, latLng) { address ->
                        val city = address ?: "Unknown Location"
                        viewModel.saveLocation(latLng, city)
                    }
                }
            },
            cameraPositionState = cameraPositionState
        ) {

            state.marker?.let { marker ->
                Marker(
                    state = MarkerState(position = marker),
                    title = "Your Location",
                    snippet = "Lat: ${marker.latitude}, Lng: ${marker.longitude}"
                )
            }
        }

        // SearchBar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
        ) {
            SearchBar(
                searchQuery = searchQuery,
                onSearch = { query ->
                    viewModel.searchLocation(query, context) { latLng ->
                        latLng?.let {
                            viewModel.addMarker(it, context)
                        } ?: Log.e("MapScreen", "Location not found for query: $query")
                    }
                }
            )
        }


        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Enter your location", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = dynamicText, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        state.marker?.let { latLng ->

                            viewModel.deleteLocation()


                            reverseGeocodeAsync(context, latLng) { location ->
                                val city = location ?: "Unknown Location"
                                viewModel.saveLocation(latLng, city)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}





@Composable
fun SearchBar(
    searchQuery: MutableState<String>,
    onSearch: (String) -> Unit,
) {
    TextField(
        value = searchQuery.value,
        onValueChange = { query -> searchQuery.value = query },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text("Search ") }, // Hint text
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon") // Search icon
        },
        trailingIcon = {
            if (searchQuery.value.isNotEmpty()) {
                IconButton(onClick = { searchQuery.value = "" }) {
                    Icon(Icons.Default.Close, contentDescription = "Clear search")
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (searchQuery.value.isNotEmpty()) {
                    onSearch(searchQuery.value)
                }
            }
        )
    )
}




fun reverseGeocodeAsync(
    context: Context,
    latLng: LatLng,
    callback: (String?) -> Unit
) {
    val geocoder = Geocoder(context, Locale.getDefault())

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        geocoder.getFromLocation(
            latLng.latitude,
            latLng.longitude,
            1,
            object : Geocoder.GeocodeListener {
                override fun onGeocode(addresses: MutableList<Address>) {
                    if (addresses.isNotEmpty()) {
                        val address = addresses[0]

                        val locality = address.adminArea ?: address.adminArea ?: "Unknown Location"
                        callback(locality)
                    } else {
                        callback("Unknown Location")
                    }
                }

                override fun onError(errorMessage: String?) {
                    callback("Error fetching location")
                }
            }
        )
    } else {
        try {
            val addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (!addressList.isNullOrEmpty()) {
                val address = addressList[0]
                val locality = address.adminArea ?: address.adminArea ?: "Unknown Location"
                callback(locality)
            } else {
                callback("Unknown Location")
            }
        } catch (e: Exception) {
            Log.e("MapScreen", "Error fetching address: ${e.message}")
            callback("Error fetching location")
        }
    }
}


@Preview
@Composable
fun Map() {
    MapScreen()
}
@Preview
@Composable
fun searchPreview() {

    val searchQuery = remember { mutableStateOf("") }


    val onSearch: (String) -> Unit = { query ->
        Log.d("Search", "Search query: $query")

    }


    SearchBar(
        searchQuery = searchQuery,
        onSearch = onSearch
    )
}




