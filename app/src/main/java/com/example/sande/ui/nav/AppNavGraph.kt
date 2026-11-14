package com.example.sande.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomNavigation
import androidx.compose.material3.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sande.ui.theme.SandeTheme
import com.example.sande.ui.screens.CatalogueScreen
import com.example.sande.ui.screens.StockScreen
import com.example.sande.ui.screens.SalesScreen
import com.example.sande.ui.screens.SettingsScreen
import com.example.sande.ui.screens.SignInScreen
import com.example.sande.ui.screens.SplashScreen
import com.example.sande.ui.screens.DashboardScreen

sealed class AppRoute {
    object Splash : AppRoute()
    object SignIn : AppRoute()
    object Shell : AppRoute()
    object Dashboard : AppRoute()
    object Catalogue : AppRoute()
    object Stock : AppRoute()
    object Sales : AppRoute()
    object Settings : AppRoute()
}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(onSignedIn = { navController.navigate("shell") }) }
        composable("signin") { SignInScreen(onSignedIn = { navController.navigate("shell") }) }
        composable("shell") {
            ShellScreen(navController = navController)
        }
        composable("dashboard") { DashboardScreen() }
        composable("catalogue") { CatalogueScreen() }
        composable("stock") { StockScreen() }
        composable("sales") { SalesScreen() }
        composable("settings") { SettingsScreen() }
    }
}

@Composable
private fun ShellScreen(navController: androidx.navigation.NavHostController) {
    val selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val routes = listOf("dashboard", "catalogue", "stock", "sales", "settings")

    Scaffold(
        bottomBar = {
            BottomNavigation(
                selectedItemIndex = selectedIndex,
                onItemClick = { index ->
                    navController.navigate(routes[index]) {
                        popUpTo(navController.graph.startDestinationRoute) {
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text(stringResource(id = com.example.sande.R.string.tab_dashboard)) },
                    selected = selectedIndex == 0,
                    onClick = {}
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Inventory, contentDescription = null) },
                    label = { Text(stringResource(id = com.example.sande.R.string.tab_catalogue)) },
                    selected = selectedIndex == 1,
                    onClick = {}
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Storage, contentDescription = null) },
                    label = { Text(stringResource(id = com.example.sande.R.string.tab_stock)) },
                    selected = selectedIndex == 2,
                    onClick = {}
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    label = { Text(stringResource(id = com.example.sande.R.string.tab_sales)) },
                    selected = selectedIndex == 3,
                    onClick = {}
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text(stringResource(id = com.example.sande.R.string.tab_settings)) },
                    selected = selectedIndex == 4,
                    onClick = {}
                )
            }
        }
    ) { innerPadding ->
        when (routes[selectedIndex]) {
            "dashboard" -> DashboardScreen(modifier = Modifier.padding(innerPadding))
            "catalogue" -> CatalogueScreen(modifier = Modifier.padding(innerPadding))
            "stock" -> StockScreen(modifier = Modifier.padding(innerPadding))
            "sales" -> SalesScreen(modifier = Modifier.padding(innerPadding))
            "settings" -> SettingsScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}
