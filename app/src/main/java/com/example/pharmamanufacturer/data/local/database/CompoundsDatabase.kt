package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.data.local.daos.CompoundsDao

interface CompoundsDatabase {
    fun compoundsDao(): CompoundsDao
}
