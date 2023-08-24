package com.example.pharmamanufacturer.data.local.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    val compound: Compound,
    val concentration: Double,
    val lowStock: Boolean = false
) : Parcelable
