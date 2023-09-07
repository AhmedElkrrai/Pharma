package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.data.local.daos.BatchesDao

interface BatchesDatabase {
    fun batchesDao(): BatchesDao
}