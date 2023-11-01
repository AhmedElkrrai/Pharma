package com.example.pharmamanufacturer.presentation.navigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val painter: Painter
)
