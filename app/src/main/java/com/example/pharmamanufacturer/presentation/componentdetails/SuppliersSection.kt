package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Supplier

@Composable
fun SuppliersSection(suppliers: List<Supplier>) {
    Column(modifier = Modifier.fillMaxSize()) {

        CenteredTitle(title = "Suppliers")

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(suppliers.size) {
                SupplierItem(supplier = suppliers[it])
            }
        }
    }
}
