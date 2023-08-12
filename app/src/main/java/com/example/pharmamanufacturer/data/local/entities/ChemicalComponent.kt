package com.example.pharmamanufacturer.data.local.entities

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = ChemicalComponent.TABLE_CHEMICAL_COMPONENT)
data class ChemicalComponent(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_CHEMICAL_COMPONENT_ID)
    val id: Int? = null,
    @ColumnInfo(name = COL_CHEMICAL_COMPONENT_NAME)
    val name: String,
    @ColumnInfo(name = COL_CHEMICAL_COMPONENT_AMOUNT)
    var amount: Double,
    @ColumnInfo(name = COL_COMPONENT_PRODUCTS)
    val products: List<String>,
    @ColumnInfo(name = COL_SUPPLIERS)
    val suppliers: List<Supplier>
) : Parcelable {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }

    companion object {
        const val TABLE_CHEMICAL_COMPONENT = "table_chemical_component"

        const val COL_CHEMICAL_COMPONENT_ID = "id"
        const val COL_CHEMICAL_COMPONENT_NAME = "name"
        const val COL_CHEMICAL_COMPONENT_AMOUNT = "amount"
        const val COL_SUPPLIERS = "suppliers"
        const val COL_COMPONENT_PRODUCTS = "component_products"
    }
}
