package com.example.pharmamanufacturer.data.models

import android.net.Uri
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChemicalComponent(
    val id: Int,
    val name: String,
    var amount: Double
) : Parcelable {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}
