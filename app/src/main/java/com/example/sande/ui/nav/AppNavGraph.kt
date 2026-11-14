package com.example.sande.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sande.ui.screens.CatalogueScreen
import com.example.sande.ui.screens.SalesScreen
import com.example.sande.ui.screens.SettingsScreen
import com.example.sande.ui.screens.SignInScreen
import com.example.sande.ui.screens.SplashScreen
import com.example.sande.ui.screens.StockScreen

private const val ROUTE_SPLASH = "splash"
private const val ROUTE_SIGN_IN = "signin"
private const val ROUTE_SHELL = "shell"

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ROUTE_SPLASH) {
        composable(ROUTE_SPLASH) {
            SplashScreen(onFinished = {
                navController.navigate(ROUTE_SIGN_IN) { popUpTo(ROUTE_SPLASH) { inclusive = true } }
            })
        }
        composable(ROUTE_SIGN_IN) {
            SignInScreen(onSignedIn = {
                navController.navigate(ROUTE_SHELL) { popUpTo(ROUTE_SIGN_IN) { inclusive = true } }
            })
        }
        composable(ROUTE_SHELL) { ShellScreen() }
    }
}

private data class Tab(val label: String, val icon: ImageVector)

@Composable
private fun ShellScreen() {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(
        Tab("Catalogue", Icons.Default.List),
        Tab("Stock", Icons.Default.Home),
        Tab("Sales", Icons.Default.ShoppingCart),
        Tab("Settings", Icons.Default.Settings)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = { selected = index },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        val m = Modifier.padding(innerPadding)
        when (selected) {
            0 -> CatalogueScreen(modifier = m)
            1 -> StockScreen(modifier = m)
            2 -> SalesScreen(modifier = m)
            else -> SettingsScreen(modifier = m)
        }
    }
}
