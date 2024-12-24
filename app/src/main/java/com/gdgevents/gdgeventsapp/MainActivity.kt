package com.gdgevents.gdgeventsapp

import ahmed.praicticing.compose_practice.permissionManager
import android.annotation.SuppressLint
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

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import com.google.android.gms.location.FusedLocationProviderClient

import com.google.android.gms.maps.model.LatLng

import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
private lateinit var PermissionManager: permissionManager

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        PermissionManager = (application as GdgApp).permissions
        setContent {

            val navController = rememberNavController()
            val isLoading = remember { mutableStateOf(true) }
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
                        composable( route = Screens.MapScreen.route,
                            arguments = listOf(
                                navArgument("latitude") { type = NavType.StringType },
                                navArgument("longitude") { type = NavType.StringType }                            )
                        ){ backStackEntry ->
                            val latitude = backStackEntry.arguments?.getString("latitude")?.toDoubleOrNull()
                            val longitude = backStackEntry.arguments?.getString("longitude")?.toDoubleOrNull()

                            if (latitude != null && longitude != null) {
                                MapScreen(LatLng(latitude, longitude))
                            } else {
                                Text("Invalid location coordinates")
                            }
                        }
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

sealed class Screens(val route: String) {
    object Permissions : Screens("permissions")
    object MapScreen   : Screens("MapScreen/{latitude}/{longitude}") {
        fun createRoute(latitude: Double, longitude: Double): String {
            Log.d("MapScreen", "createRoute: $latitude,$longitude")
            return "MapScreen/$latitude/$longitude"
        }
    }
}