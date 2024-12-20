package com.gdgevents.gdgeventsapp

import ahmed.praicticing.compose_practice.permissionManager
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var fusedLocationClient: FusedLocationProviderClient
private lateinit var PermissionManager: permissionManager
private lateinit var defaultCameraPosition: CameraPosition
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PermissionManager = (application as MyApplication).permissions

        val PermissionManager = permissionManager(this)
        setContent {
            var selectedLocation by remember { mutableStateOf<LatLng?>(LatLng(22.0, 22.0)) }
            var locationText by remember { mutableStateOf<String>("") }
            val cameraPositionState = rememberCameraPositionState()
            val navController = rememberNavController()
            val isLoading = remember { mutableStateOf(true) }
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            LaunchedEffect(PermissionManager.hasAllPermissions) {
                if (PermissionManager.hasAllPermissions) {
                    // Fetch current location
                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        location?.let {
                            val latLng = LatLng(it.latitude, it.longitude)
                            selectedLocation = latLng
                            defaultCameraPosition = CameraPosition.fromLatLngZoom(latLng, 17f)
                            cameraPositionState.position =
                                defaultCameraPosition
                            locationText = getCityAndCountryFromCoordinates(this@MainActivity,latLng)

                        } ?: run {
                            Log.d("Location", "Location is null.")
                        }
                    }
                } else {
                    PermissionManager.checkPermissions()
                }
            }

            GDGEventsAppTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    if (isLoading.value) {
                        LoadingIndicator() // Show loading indicator
                    } else {
                        // After location is fetched, navigate to MapScreen
                        navController.navigate(Screens.MapScreen.route)
                    }

                    NavHost(
                        navController = navController,
                        startDestination = Screens.Permissions.route
                    ) {
                        composable("permissions") {
                            PermissionScreen(navController = navController)
                        }
                        composable("MapScreen") { selectedLocation?.let { it1 -> MapScreen(it1) } }
                        Log.d("After MapScreen Composable", "onCreate: $selectedLocation")
                    }
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        if(!permissionManager(this).hasAllPermissions) {
            lifecycleScope.launch {
            permissionManager(this@MainActivity).checkPermissions()
        }
            }
    }

}
@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .width(50.dp)
            .padding(16.dp)
            .background(Color.Green),
        color = Color.Blue
    )
}
@SuppressLint("MissingPermission")
@Composable
fun FetchLocation(isLoading: MutableState<Boolean>, onLocationFetched: (LatLng?) -> Unit) {
    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    LaunchedEffect(Unit) {
        isLoading.value = true
        Log.d("IsLoading ", "${isLoading.value}")
        try {
            val locationResult = fusedLocationClient.lastLocation
            onLocationFetched(locationResult.let { LatLng(it.result.latitude, it.result.longitude) })
        } catch (e: Exception) {
            Log.d("Location", "Failed to fetch location: ${e.message}")
            onLocationFetched(null)
        } finally {
            isLoading.value = false
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GDGEventsAppTheme {
        Greeting("Android")
    }
}

sealed class Screens(val route: String) {
    object Permissions : Screens("permissions")
    object MapScreen : Screens("MapScreen")

}