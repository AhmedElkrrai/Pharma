package com.example.pharmamanufacturer.presentation.productdetails

interface ProductDetailsScreenListener {
    fun navigateBack()
    fun onEditClick(productId: String)
    fun onTabSelected(tab: ProductDetailsTab)
}
