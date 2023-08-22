package com.example.pharmamanufacturer.presentation.products

import com.example.pharmamanufacturer.data.local.entities.Product

interface ProductsScreenListener {
    fun onItemClick(product: Product)
    fun onAddClick()
}
