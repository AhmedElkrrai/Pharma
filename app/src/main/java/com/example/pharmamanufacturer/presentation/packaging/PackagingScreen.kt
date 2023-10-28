package com.example.pharmamanufacturer.presentation.packaging

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen

@Composable
fun PackagingScreen() {
    EmptyContentScreen(
        modifier = Modifier.fillMaxSize(),
        message = "Please Add Packaging to Product(s)..",
        animationResource = R.raw.waiting
    )
}
