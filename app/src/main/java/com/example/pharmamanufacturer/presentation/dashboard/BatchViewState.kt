package com.example.pharmamanufacturer.presentation.dashboard

import com.example.pharmamanufacturer.data.local.entities.Product

data class BatchViewState(
    val number: String,
    val date: String,
    val product: Product
)
