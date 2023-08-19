package com.example.pharmamanufacturer.data.utils

import androidx.room.TypeConverter
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
}
