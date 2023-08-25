package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_BATCHES
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductItem

@Composable
fun ProductsSection(
    compound: Compound,
    products: List<Product>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
    ) {

        CenteredTitle(title = "Products")

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (products.isEmpty()) return

        LazyColumn {
            items(products) { product ->
                val batch =
                    product
                        .batches
                        .find { it.id == compound.id }

                batch?.let {
                    ProductItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        product = product,
                        lowStock = (compound.availableAmount / it.concentration) < MINIMUM_PRODUCT_BATCHES
                    )
                }
            }
        }
    }
}
