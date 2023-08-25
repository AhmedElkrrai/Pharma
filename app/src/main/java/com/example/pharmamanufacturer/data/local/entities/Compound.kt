package com.example.pharmamanufacturer.data.local.entities

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_BATCHES
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
    val batches: List<Batch>? = null,

    @ColumnInfo(name = COL_SUPPLIERS)
    val suppliers: List<Supplier>? = null
) : Parcelable {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }

    val lowStock: Boolean
        get() {
            if (batches.isNullOrEmpty())
                return false

            val availableBatches = getAvailableBatches()
            return availableBatches > MINIMUM_PRODUCT_BATCHES
        }

    private fun getAvailableBatches(): Double {
        return batches?.minOfOrNull { it.available } ?: 0.0
    }

    companion object {
        const val TABLE_COMPOUND = "table_compound"

        const val COL_COMPOUND_ID = "id"
        const val COL_COMPOUND_NAME = "name"
        const val COL_AVAILABLE_AMOUNT = "available_amount"
        const val COL_SUPPLIERS = "suppliers"
        const val COL_COMPOUND_PRODUCTS = "compound_products"
    }
}
