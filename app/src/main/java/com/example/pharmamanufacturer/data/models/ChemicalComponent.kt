package com.example.pharmamanufacturer.data.models

data class ChemicalComponent(
    val name: String,
    val amount: Double
)

val componentsList = listOf(
    ChemicalComponent("H2", 100.0),
    ChemicalComponent("N", 40.0),
    ChemicalComponent("O", 22.0),
    ChemicalComponent("S", 67.0),
    ChemicalComponent("Na", 98.0),
    ChemicalComponent("P", 38.0),
    ChemicalComponent("C", 21.0),
    ChemicalComponent("L", 62.0),
    ChemicalComponent("Ge", 15.0),
    ChemicalComponent("Ma", 19.0),
    ChemicalComponent("U", 33.0),
)
