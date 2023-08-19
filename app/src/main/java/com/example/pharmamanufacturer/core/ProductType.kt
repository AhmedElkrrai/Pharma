package com.example.pharmamanufacturer.core

import android.os.Bundle
import androidx.navigation.NavType
import com.example.pharmamanufacturer.data.local.entities.Product
import com.google.gson.Gson

@JvmField
val ProductType: NavType<Product?> = object : NavType<Product?>(true) {
    override fun get(bundle: Bundle, key: String): Product? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Product {
        return Gson().fromJson(value, Product::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Product?) {
        bundle.putParcelable(key, value)
    }
}
