package com.example.pharmamanufacturer.presentation.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen

@Composable
fun DashboardScreen() {
    EmptyContentScreen(
        modifier = Modifier.fillMaxSize(),
        message = "Please Start Production..",
        animationResource = R.raw.chemical_reaction
    )
}
