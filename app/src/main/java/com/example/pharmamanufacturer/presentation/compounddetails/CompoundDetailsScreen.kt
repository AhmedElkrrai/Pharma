package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun CompoundDetailsScreen(
    compound: Compound,
    products: List<Product>,
    listener: CompoundDetailsScreenListener
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = compound.name,
            modifier = Modifier.fillMaxWidth(),
            onBackClick = { listener.navigateBack() },
            onEditClick = { listener.onEditClick() }
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        DetailsRow(
            details = compound.availableAmount.round().toString()
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (compound.batches?.isNotEmpty() == true) {
            val modifier = if (products.size < 3)
                Modifier.fillMaxWidth()
            else Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)

            ProductsSection(
                modifier = modifier,
                compound = compound,
                products = products
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (compound.suppliers.isNullOrEmpty()) {
            EmptyContentScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                message = "Please Add a Supplier..",
                animationResource = R.raw.tumbleweed
            )
        } else
            SuppliersSection(compound.suppliers)
    }
}
