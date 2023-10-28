package com.example.pharmamanufacturer.data.local.entities

data class MaterialNode(
    val id: Int,
    val neededAmount: Double,
    val available: Double? = null
)
