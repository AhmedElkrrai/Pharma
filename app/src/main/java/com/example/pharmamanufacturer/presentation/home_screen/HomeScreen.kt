package com.example.pharmamanufacturer.presentation.home_screen

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
import com.example.pharmamanufacturer.data.models.BottomNavItem
import com.example.pharmamanufacturer.presentation.Screen

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = stringResource(id = R.string.title_products),
                        route = Screen.ProductsScreen.rout,
                        painter = painterResource(id = R.drawable.health_icon)
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.title_components),
                        route = Screen.ComponentsScreen.rout,
                        painter = painterResource(id = R.drawable.ic_medicine_component),
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.title_packing),
                        route = Screen.PackingScreen.rout,
                        painter = painterResource(id = R.drawable.ic_packing),
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
