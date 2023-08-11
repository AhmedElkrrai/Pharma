package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.core.UiDimensions

@Composable
fun ProductsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {

        CenteredTitle(title = "Products")

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

        LazyColumn {
            val products = listOf("Na", "Cl")
            items(products) { productName ->
                ComponentProductItem(
                    modifier = Modifier.fillMaxWidth(),
                    name = productName
                )
            }
        }
    }
}
