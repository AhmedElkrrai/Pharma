package com.example.pharmamanufacturer.presentaion.home_screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.data.models.BottomNavItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Chat",
                        route = "chat",
                        painter = painterResource(id = R.drawable.health_icon)
                    ),
                    BottomNavItem(
                        name = "Components",
                        route = "components",
                        painter = painterResource(id = R.drawable.ic_medicine_component),
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = "settings",
                        painter = painterResource(id = R.drawable.ic_packing),
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Navigation(navController = navController)
    }
}
