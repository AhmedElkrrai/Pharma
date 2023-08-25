package com.example.pharmamanufacturer.data.local.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Batch(
    val id: Int,
    val concentration: Double,
    val available: Double? = null
) : Parcelable
