package com.example.pharmamanufacturer.core

fun Double.round(decimals: Int = 1): Double = "%.${decimals}f".format(this).toDouble()

fun String.capitalizeFirstChar(): String = this.lowercase().replaceFirstChar { it.uppercaseChar() }.trim()
