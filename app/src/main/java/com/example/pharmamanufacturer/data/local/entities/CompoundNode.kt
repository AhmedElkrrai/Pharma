package com.example.pharmamanufacturer.data.local.entities

data class CompoundNode(
    val id: Int,
    val concentration: Double,
    val available: Double? = null
)
