package com.example.pharmamanufacturer.presentation.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun ProductDetailsScreen(
    product: Product,
    listener: ProductDetailsScreenListener
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = product.name,
            modifier = Modifier.fillMaxWidth(),
            onBackClick = {
                listener.navigateBack()
            }
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        val availableBatches = product.getAvailableBatches()
        val unit = if (availableBatches > 1) " Batches" else " Batch"

        DetailsRow(
            details = availableBatches.toString(),
            unit = unit
        )
    }
}
