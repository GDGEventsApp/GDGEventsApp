package com.gdgevents.gdgeventsapp

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdgevents.gdgeventsapp.ui.theme.GDGEventsAppTheme
import com.gdgevents.gdgeventsapp.ui.theme.ProductSans

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {

            NavigationBar(
                backgroundColor = Color(0xFFF4F4F4),
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 8.dp,
                modifier = Modifier.padding(WindowInsets.navigationBars.asPaddingValues()).height(56.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                topLevelRoutes.forEach { topLevelRoute ->
                    val isSelected = currentDestination?.hierarchy?.any {
                        it.route == topLevelRoute.route
                    } == true
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = if (isSelected) topLevelRoute.selectedIcon else topLevelRoute.unselectedIcon),
                                contentDescription = topLevelRoute.name,
                                tint = if (isSelected) Color(0xFF1A73E8) else Color.Gray,
                                modifier = Modifier.size(20.dp)

                            )
                        },
                        label = {
                            Text(
                                text = topLevelRoute.name,
                                style = TextStyle(
                                    fontFamily = ProductSans,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 10.sp,
                                    lineHeight = 16.98.sp,
                                    color = if (isSelected) Color(0xFF1A73E8) else Color.Gray // Primary color for selected
                                )
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home_route",
            Modifier.padding(innerPadding)
        ) {
            composable(topLevelRoutes[0].route) { PlaceholderScreen(topLevelRoutes[0].name) }
            composable(topLevelRoutes[1].route) { PlaceholderScreen(topLevelRoutes[1].name) }
            composable(topLevelRoutes[2].route) { PlaceholderScreen(topLevelRoutes[2].name) }
            composable(topLevelRoutes[3].route) { PlaceholderScreen(topLevelRoutes[3].name) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    GDGEventsAppTheme {
        MainScreen()
    }
}