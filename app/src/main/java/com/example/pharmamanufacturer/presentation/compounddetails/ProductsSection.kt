package com.example.pharmamanufacturer.presentation.compounddetails

import android.util.Log
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
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductItem

@Composable
fun ProductsSection(
    compound: Compound
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
    ) {

        CenteredTitle(title = "Products")

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        val products = compound.products
        if (products.isNullOrEmpty()) return

        LazyColumn {
            items(products) { product ->
                val ingredient =
                    product
                        .ingredients
                        .find { it.compound.name == compound.name }

                Log.d(
                    "taggs",
                    "compound.id = ${compound.id}, ingredient = ${ingredient?.compound?.id}"
                )

                ingredient?.let {
                    ProductItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        product = product,
                        lowStock = it.concentration > compound.availableAmount
                    )
                }
            }
        }
    }
}
