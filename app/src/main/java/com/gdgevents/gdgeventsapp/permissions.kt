package com.gdgevents.gdgeventsapp


import ahmed.praicticing.compose_practice.permissionManager.Companion.REQUIRED_PERMISSIONS_POST_T
import ahmed.praicticing.compose_practice.permissionManager.Companion.REQUIRED_PERMISSIONS_PRE_T
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Done

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.gdgevents.gdgeventsapp.MainActivity
import com.gdgevents.gdgeventsapp.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen(
    navController: NavHostController,
    viewModel: PermissionViewModel = viewModel(factory = PermissionViewModelFactory())
) {

    val state = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var hasRequestedPermissions by remember { mutableStateOf(false) }

    val requestPermissions =
        rememberLauncherForActivityResult(RequestMultiplePermissions()) { permissions ->
            hasRequestedPermissions = true
            viewModel.onPermissionChange(permissions)
        }

    fun openSettings() {
        ContextCompat.startActivity(context, viewModel.createSettingsIntent(), null)
    }

    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Permissions needed", fontFamily = FontFamily.Serif)

            Text(
                modifier = Modifier.padding(16.dp),
                text = "You have to grant access to these permissions in order to use the app"
            )
            ListItem(
                headlineContent = { Text("Location access") },
                trailingContent = { PermissionAccessIcon(state.value.hasAllAccess) },
                leadingContent = {
                    Icon(
                        Icons.Filled.Done,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
            )
            Divider()
            Spacer(Modifier.height(32.dp))
            if (state.value.hasAllAccess) {
                FilledTonalButton(onClick = { navController.navigate(Screens.MapScreen.route) }) {
                    Text("Get started")
                }
            } else {
                if (hasRequestedPermissions) {
                    FilledTonalButton(onClick = { openSettings() }) {
                        Text("Go to settings")
                    }
                } else {
                    FilledTonalButton(onClick = {
                        if (Build.VERSION.SDK_INT >= 33) {
                            requestPermissions.launch(REQUIRED_PERMISSIONS_POST_T)
                        }
                        else {
                            requestPermissions.launch(REQUIRED_PERMISSIONS_PRE_T)
                        }
                    }) {
                        Text("Request permissions")
                    }
                }
            }
        }
    }
}

@Composable
fun PermissionAccessIcon(hasAccess: Boolean) {
    if (hasAccess) {
        Icon(
            Icons.Filled.Check,
            contentDescription = "Permission accepted"
        )
    } else {
        Icon(
            Icons.Filled.Close,
            contentDescription = "Permission not granted"
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PermissionScreenPreview(){
    PermissionScreen(navController = NavHostController(LocalContext.current))

}