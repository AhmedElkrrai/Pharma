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
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductItem

@Composable
fun ProductsSection(products: List<Product>) {
    Column(modifier = Modifier.fillMaxWidth()) {

        CenteredTitle(title = "Products")

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        LazyColumn {
            items(products) { product ->
                ProductItem(
                    modifier = Modifier.fillMaxWidth(),
                    product = product
                )
            }
        }
    }
}
