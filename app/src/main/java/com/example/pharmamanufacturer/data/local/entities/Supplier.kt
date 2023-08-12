package com.example.pharmamanufacturer.data.local.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Supplier(
    val name: String,
    val capacity: Double
) : Parcelable
