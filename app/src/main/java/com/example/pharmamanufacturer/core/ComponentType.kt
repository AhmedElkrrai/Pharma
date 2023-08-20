package com.example.pharmamanufacturer.core

import android.os.Bundle
import androidx.navigation.NavType
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.google.gson.Gson

@JvmField
val CompoundType: NavType<Compound?> = object : NavType<Compound?>(true) {
    override fun get(bundle: Bundle, key: String): Compound? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Compound {
        return Gson().fromJson(value, Compound::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Compound?) {
        bundle.putParcelable(key, value)
    }
}
