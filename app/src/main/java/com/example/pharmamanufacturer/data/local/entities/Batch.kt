package com.example.pharmamanufacturer.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Batch.TABLE_BATCH)
data class Batch(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_BATCH_ID)
    val id: Int? = null,

    @ColumnInfo(name = COL_BATCH_NUMBER)
    val number: String,

    @ColumnInfo(name = COL_PRODUCT_ID)
    val productId: Int
) {

    companion object {
        const val TABLE_BATCH = "table_batch"

        const val COL_BATCH_ID = "id"
        const val COL_BATCH_NUMBER = "batch_number"
        const val COL_PRODUCT_ID = "product_id"
    }
}
