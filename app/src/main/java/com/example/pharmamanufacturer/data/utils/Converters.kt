package com.example.pharmamanufacturer.data.utils

import androidx.room.TypeConverter
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun supplierToJson(value: List<Supplier>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToSupplier(value: String) = Gson().fromJson(value, Array<Supplier>::class.java).toList()

    @TypeConverter
    fun compoundToJson(value: List<Compound>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToCompound(value: String) = Gson().fromJson(value, Array<Compound>::class.java).toList()

    @TypeConverter
    fun productsToJson(value: List<Product>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToProduct(value: String) = Gson().fromJson(value, Array<Product>::class.java).toList()
}
