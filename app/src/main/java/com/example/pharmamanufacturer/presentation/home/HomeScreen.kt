package com.example.pharmamanufacturer.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.models.BottomNavItem
import com.example.pharmamanufacturer.core.Screen

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = stringResource(id = R.string.title_products),
                        route = Screen.ProductsScreen.route,
                        painter = painterResource(id = R.drawable.ic_health)
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.title_components),
                        route = Screen.ComponentsScreen.route,
                        painter = painterResource(id = R.drawable.ic_medicine),
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.title_packaging),
                        route = Screen.PackagingScreen.route,
                        painter = painterResource(id = R.drawable.ic_packaging),
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Navigation(navController = navController)
        }
    }
}
