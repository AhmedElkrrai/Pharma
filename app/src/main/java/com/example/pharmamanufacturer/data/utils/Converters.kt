package com.example.pharmamanufacturer.data.utils

import androidx.room.TypeConverter
import com.example.pharmamanufacturer.data.local.entities.CompoundNode
import com.example.pharmamanufacturer.data.local.entities.ProductNode
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun suppliersToJson(value: List<Supplier>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToSuppliers(value: String?) = Gson().fromJson(value, Array<Supplier>::class.java)?.toList()

    @TypeConverter
    fun productNodesToJson(value: List<ProductNode>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToProductNodes(value: String?) = Gson().fromJson(value, Array<ProductNode>::class.java)?.toList()

    @TypeConverter
    fun compoundNodesToJson(value: List<CompoundNode>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToCompoundNodes(value: String) = Gson().fromJson(value, Array<CompoundNode>::class.java)?.toList()
}
