package com.gdgevents.gdgeventsapp.features.map.presentaion

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdgevents.gdgeventsapp.PermissionManager
import com.gdgevents.gdgeventsapp.R
import com.gdgevents.gdgeventsapp.ui.theme.LocationDetailsBackground
import com.gdgevents.gdgeventsapp.ui.theme.LocationIconBackground
import com.gdgevents.gdgeventsapp.ui.theme.PrimaryLight
import com.gdgevents.gdgeventsapp.ui.theme.UnEnabledContainerColor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = true) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle("")
    val searchQuery = remember { mutableStateOf("") }
    val isShowsGpsDialog = remember { mutableStateOf(false) }
    val isGpsEnabled = remember { mutableStateOf(false) }

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) viewModel.fetchUserLocation()
        }
    )

    LaunchedEffect(Unit) {
        isGpsEnabled.value = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!isGpsEnabled.value) isShowsGpsDialog.value = true
        if (!PermissionManager.hasLocationPermission(context)) {
            PermissionManager.requestLocationPermission(
                launcher = requestPermissionLauncher
            )
        }
    }

    if (message.isNotEmpty()) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val cameraPositionState = rememberCameraPositionState()

        LaunchedEffect(state.marker) {
            state.marker?.let { marker ->
                val location = LatLng(marker.latitude, marker.longitude)
                cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 15f)
            }
        }

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
                                containerColor = PrimaryLight
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
                            colors = ButtonDefaults.buttonColors(containerColor = LocationDetailsBackground),
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

        MapContent(
            marker = state.marker,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                viewModel.addMarker(latLng)
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
                viewModel.searchLocation(
                    query = query
                ) { latLng ->
                    latLng?.let {
                        viewModel.addMarker(it)
                    } ?: Log.e("MapScreen", "Location not found for query: $query")
                }
            }
        )

        BottomSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            marker = state.marker,
            regionName = state.regionName,
            onConfirmClick = {
                viewModel.saveLocation()
            })
    }
}

@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    marker: LatLng?,
    regionName: String,
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

        if (marker == null) {
            Text(
                text = "Choose your location to start to find events around you",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        } else {
            Row(
                modifier = modifier
                    .clip(RoundedCornerShape(3.dp))
                    .fillMaxWidth()
                    .background(LocationDetailsBackground)
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
                        .background(LocationIconBackground)
                        .size(35.dp, 35.dp)
                )

                Text(
                    text = regionName,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier.padding(start = 10.dp)
                )
            }
        }

        Button(
            shape = RoundedCornerShape(6.dp),
            enabled = marker != null,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = UnEnabledContainerColor,
                containerColor = PrimaryLight
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

@Composable
fun MapContent(
    marker: LatLng? = null,
    uiSettings: MapUiSettings,
    cameraPositionState: CameraPositionState,
    onMapClick: (LatLng) -> Unit
) {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = MapProperties(),
        uiSettings = uiSettings,
        onMapClick = onMapClick,
        cameraPositionState = cameraPositionState
    ) {
        marker?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Your Location",
                snippet = "Lat: ${it.latitude}, Lng: ${it.longitude}"
            )
        }
    }
}