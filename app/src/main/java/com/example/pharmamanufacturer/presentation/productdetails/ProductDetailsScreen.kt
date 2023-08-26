package com.example.pharmamanufacturer.presentation.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_BATCHES
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.theme.Green
import com.example.pharmamanufacturer.presentation.theme.Red
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun ProductDetailsScreen(
    product: Product,
    compounds: List<Compound>,
    listener: ProductDetailsScreenListener
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = product.name,
            modifier = Modifier.fillMaxWidth(),
            onBackClick = { listener.navigateBack() },
            onEditClick = { listener.onEditClick() }
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        val availableBatches = product.getAvailableBatches()
        val unit = if (availableBatches >= 2) " Batches" else " Batch"
        val detailsColor = if (availableBatches < MINIMUM_PRODUCT_BATCHES) Red else Green

        DetailsRow(
            details = availableBatches.toString(),
            unit = unit,
            detailsColor = detailsColor
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitle(title = stringResource(id = R.string.title_compounds))

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        LazyColumn {
            items(compounds) { compound ->
                val concentration =
                    product.batches.find { it.id == compound.id }?.concentration ?: 0.0

                ProductCompoundItem(
                    name = compound.name,
                    availableAmount = compound.availableAmount,
                    concentration = concentration
                )
            }
        }
    }
}
