package com.example.pharmamanufacturer.presentation.packing

import androidx.compose.runtime.Composable
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen

@Composable
fun PackagingScreen() {
    EmptyContentScreen(
        message = "Please Start Production..",
        animationResource = R.raw.waiting
    )
}
