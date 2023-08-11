package com.example.pharmamanufacturer.core

fun Double.round(decimals: Int = 1): Double = "%.${decimals}f".format(this).toDouble()
