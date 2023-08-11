package com.example.pharmamanufacturer.presentation.products

import androidx.compose.runtime.Composable
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.utilityscreens.EmptyContentScreen

@Composable
fun ProductsScreen() {
    EmptyContentScreen(
        message = "Please add a Product..",
        animationResource = R.raw.cat
    )
}
