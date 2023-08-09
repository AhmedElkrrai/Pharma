package com.example.pharmamanufacturer.data.local.database

import com.example.pharmamanufacturer.data.local.daos.ChemicalComponentDao

interface ChemicalComponentDatabase {
    fun chemicalComponentDao(): ChemicalComponentDao
}
