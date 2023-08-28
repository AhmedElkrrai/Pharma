package com.example.pharmamanufacturer.data.local.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductNode(
    val id: Int,
    val concentration: Double
) : Parcelable
