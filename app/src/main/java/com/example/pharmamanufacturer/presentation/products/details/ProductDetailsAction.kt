package com.example.pharmamanufacturer.presentation.products.details

internal sealed interface ProductDetailsAction {
    data class SelectTab(val tab: ProductDetailsTab) : ProductDetailsAction
}
