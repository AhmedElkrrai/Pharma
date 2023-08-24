package com.example.pharmamanufacturer.data.local.entities

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Compound.TABLE_COMPOUND)
data class Compound(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_COMPOUND_ID)
    val id: Int? = null,

    @ColumnInfo(name = COL_COMPOUND_NAME)
    val name: String,

    @ColumnInfo(name = COL_AVAILABLE_AMOUNT)
    var availableAmount: Double,

    @ColumnInfo(name = COL_COMPOUND_PRODUCTS)
    val productsIds: List<Int>? = null,

    @ColumnInfo(name = COL_COMPOUND_LOW_STOCK)
    var lowStock: Boolean = false,

    @ColumnInfo(name = COL_SUPPLIERS)
    val suppliers: List<Supplier>? = null
) : Parcelable {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }

    companion object {
        const val TABLE_COMPOUND = "table_compound"

        const val COL_COMPOUND_ID = "id"
        const val COL_COMPOUND_NAME = "name"
        const val COL_AVAILABLE_AMOUNT = "available_amount"
        const val COL_COMPOUND_LOW_STOCK = "compound_low_stock"
        const val COL_SUPPLIERS = "suppliers"
        const val COL_COMPOUND_PRODUCTS = "compound_products"
    }
}
