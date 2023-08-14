package com.example.pharmamanufacturer.presentation.models

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val painter: Painter
)
