package com.example.pharmamanufacturer.data.local.entities

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = Product.TABLE_PRODUCT)
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_PRODUCT_ID)
    val id: Int? = null,

    @ColumnInfo(name = COL_PRODUCT_NAME)
    val name: String,

    @ColumnInfo(name = COL_PRODUCT_LOW_STOCK)
    var lowStock: Boolean,

    @ColumnInfo(name = COL_PRODUCT_BATCHES)
    val batches: List<String>,

    @ColumnInfo(name = COL_PRODUCT_COMPONENT)
    val components: List<ChemicalComponent>
) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }

    companion object {
        const val TABLE_PRODUCT = "table_product"

        const val COL_PRODUCT_ID = "id"
        const val COL_PRODUCT_NAME = "product_name"
        const val COL_PRODUCT_LOW_STOCK = "product_low_stock"
        const val COL_PRODUCT_BATCHES = "batches"
        const val COL_PRODUCT_COMPONENT = "components"
    }
}
