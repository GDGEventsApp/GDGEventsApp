package com.gdgevents.gdgeventsapp.features.map.presentaion

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.features.map.data.MapState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = true) }
    val state by viewModel.state.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    val hasLocationPermission = remember {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled: Boolean = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val isShowsGpsDialog = remember { mutableStateOf(false) }

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
        if (!isGpsEnabled) isShowsGpsDialog.value = true
        if (!hasLocationPermission) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            viewModel.fetchUserLocation(context)
        }
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val cameraPositionState = rememberCameraPositionState()

        // GPS dialog
        if (isShowsGpsDialog.value) {
            BasicAlertDialog(onDismissRequest = {
                isShowsGpsDialog.value = false
            }) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .widthIn(
                            min = 70.dp,
                            max = 110.dp
                        )

                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = Color.White)
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            stringResource(R.string.disables_gps_dialog_title),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            stringResource(R.string.disabled_gps_dialog_description),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                        )

                        Button(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.enabled_container_color)
                            ),
                            onClick = {
                                context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                            }) {
                            Text(stringResource(R.string.ok))
                        }

                        Button(
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.gray)),
                            onClick = {
                                isShowsGpsDialog.value = false
                            }) {
                            Text(stringResource(R.string.cancel), color = Color.Black)
                        }
                    }

                    Image(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                            .clickable {
                                isShowsGpsDialog.value = false
                            }
                    )
                }
            }
        }


        LaunchedEffect(state.marker) {
            state.marker?.let { marker ->
                val savedLatLng = LatLng(marker.latitude, marker.longitude)
                cameraPositionState.position = CameraPosition.fromLatLngZoom(savedLatLng, 15f)
            }
        }

        MapContent(
            state = state,
            uiSettings = uiSettings,
            hasLocationPermission = hasLocationPermission,
            requestPermissionLauncher = requestPermissionLauncher,
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                viewModel.addMarker(latLng, context)
            }
        )

        // SearchBar
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .align(Alignment.TopCenter),
            searchQuery = searchQuery,
            onSearch = { query ->
                viewModel.searchLocation(query, context) { latLng ->
                    latLng?.let {
                        viewModel.addMarker(it, context)
                    } ?: Log.e("MapScreen", "Location not found for query: $query")
                }
            }
        )

        BottomSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            state = state,
            onConfirmClick = {
                viewModel.saveLocation()
            })
    }
}

@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    state: MapState,
    onConfirmClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
            .padding(bottom = 50.dp)
    ) {
        Text(
            text = "Enter your location",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        if (state.marker == null) {
            Text(
                text = "Choose your location to start to find events around you",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        if (state.marker != null) {
            Row(
                modifier = modifier
                    .clip(RoundedCornerShape(3.dp))
                    .fillMaxWidth()
                    .background(colorResource(R.color.gray))
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    contentScale = ContentScale.None,
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(colorResource(R.color.location_icon_background))
                        .size(35.dp, 35.dp)
                )

                Text(
                    text = state.locationText.toString(),
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
        }

        Button(
            shape = RoundedCornerShape(6.dp),
            enabled = state.marker != null,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = colorResource(R.color.un_enabled_container_color),
                containerColor = colorResource(R.color.enabled_container_color)
            ),
            onClick = onConfirmClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text(stringResource(R.string.confirm), color = Color.White)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: MutableState<String>,
    onSearch: (String) -> Unit,
) {
    TextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            disabledContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        value = searchQuery.value,
        onValueChange = { query -> searchQuery.value = query },
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(5.dp)),
        placeholder = { Text(stringResource(R.string.search_hint)) }, // Hint text
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

@Composable
fun MapContent(
    state: MapState,
    uiSettings: MapUiSettings,
    hasLocationPermission: Boolean,
    requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
    cameraPositionState: CameraPositionState,
    onMapClick: (LatLng) -> Unit
) {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = state.properties,
        uiSettings = uiSettings,
        onMapClick = { latLng ->
            if (!hasLocationPermission) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            } else {
                onMapClick(latLng)
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
}