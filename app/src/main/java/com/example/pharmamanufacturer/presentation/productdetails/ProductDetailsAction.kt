package com.example.pharmamanufacturer.presentation.productdetails

internal sealed interface ProductDetailsAction {
    data class SelectTab(val tab: ProductDetailsTab) : ProductDetailsAction
}
