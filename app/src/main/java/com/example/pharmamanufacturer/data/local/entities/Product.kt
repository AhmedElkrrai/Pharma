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
@Entity(tableName = Product.TABLE_PRODUCT)
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_PRODUCT_ID)
    val id: Int? = null,

    @ColumnInfo(name = COL_PRODUCT_NAME)
    val name: String,

    @ColumnInfo(name = COL_COMPOUND_NODES)
    val compoundNodes: List<CompoundNode>

) : Parcelable {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }

    val lowStock: Boolean
        get() {
            val availableBatches = getAvailableBatches()
            return availableBatches < MINIMUM_PRODUCT_BATCHES
        }

    fun getAvailableBatches(): Double {
        return compoundNodes
            .map { it.available }
            .sortedWith(compareBy { it }).first()
            ?: 0.0
    }

    companion object {
        const val TABLE_PRODUCT = "table_product"

        const val COL_PRODUCT_ID = "id"
        const val COL_PRODUCT_NAME = "product_name"
        const val COL_COMPOUND_NODES = "compound_nodes"
    }
}
