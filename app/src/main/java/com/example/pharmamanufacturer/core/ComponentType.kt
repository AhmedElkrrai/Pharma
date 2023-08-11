package com.example.pharmamanufacturer.core

import android.os.Bundle
import androidx.navigation.NavType
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.google.gson.Gson

@JvmField
val ComponentType: NavType<ChemicalComponent?> = object : NavType<ChemicalComponent?>(true) {
    override fun get(bundle: Bundle, key: String): ChemicalComponent? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ChemicalComponent {
        return Gson().fromJson(value, ChemicalComponent::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ChemicalComponent?) {
        bundle.putParcelable(key, value)
    }
}
