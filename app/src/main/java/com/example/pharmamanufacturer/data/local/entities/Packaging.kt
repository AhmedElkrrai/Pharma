package com.example.pharmamanufacturer.data.local.entities

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_BATCHES
import com.google.gson.Gson

@Entity(tableName = Packaging.TABLE_PACKAGING)
data class Packaging(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_PACKAGING_ID)
    val id: Int? = null,

    @ColumnInfo(name = COL_PACKAGING_TYPE)
    val type: String,

    @ColumnInfo(name = COL_AVAILABLE_AMOUNT)
    var availableAmount: Double,

    @ColumnInfo(name = COL_PRODUCTS_NODES)
    val productNodes: List<ProductNode>? = null
) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }

    val lowStock: Boolean
        get() {
            if (productNodes.isNullOrEmpty())
                return false

            return getAvailableBatches() < MINIMUM_PRODUCT_BATCHES
        }

    private fun getAvailableBatches(): Double {
        return productNodes?.minOfOrNull { availableAmount / it.neededAmount } ?: 0.0
    }

    companion object {
        const val TABLE_PACKAGING = "table_packaging"

        const val COL_PACKAGING_ID = "id"
        const val COL_PACKAGING_TYPE = "type"
        const val COL_AVAILABLE_AMOUNT = "available_amount"
        const val COL_PRODUCTS_NODES = "packaging_products"
    }
}
