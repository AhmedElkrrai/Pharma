package com.example.pharmamanufacturer.presentation.products.details

interface ProductDetailsScreenListener {
    fun navigateBack()
    fun onEditClick(productId: String, productName: String)
    fun onTabSelected(tab: ProductDetailsTab)
}
