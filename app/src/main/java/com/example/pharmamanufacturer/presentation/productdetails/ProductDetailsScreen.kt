package com.example.pharmamanufacturer.presentation.productdetails

import androidx.compose.runtime.Composable
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle

@Composable
fun ProductDetailsScreen(
    product: Product?,
    listener: ProductDetailsScreenListener
) {
    if (product == null) return

    CenteredTitle(title = product.name)

}
