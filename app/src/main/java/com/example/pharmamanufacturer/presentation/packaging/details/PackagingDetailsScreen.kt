package com.example.pharmamanufacturer.presentation.packaging.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.presentation.utilitycompose.SuppliersSection
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductsSection
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun PackagingDetailsScreen(
    viewState: PackagingDetailsViewState,
    listener: PackagingDetailsScreenListener
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = viewState.packaging?.type ?: "",
            modifier = Modifier.fillMaxWidth(),
            onBackClick = { listener.navigateBack() },
            onEditClick = {
                listener.onEditClick(
                    type = viewState.packaging?.type
                )
            }
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        DetailsRow(
            details = viewState.packaging?.availableAmount?.round().toString(),
            unit = " Unit"
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (viewState.packaging?.productNodes?.isNotEmpty() == true) {
            val modifier = if (viewState.products.size < 3)
                Modifier.fillMaxWidth()
            else Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)

            ProductsSection(
                modifier = modifier,
                materialId = viewState.packaging.id!!,
                availableAmount = viewState.packaging.availableAmount,
                products = viewState.products,
                screen = Screen.PackagingDetailsScreen
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (viewState.packaging?.suppliers.isNullOrEmpty()) {
            EmptyContentScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                message = "Please Add a Supplier..",
                animationResource = R.raw.waiting
            )
        } else
            viewState.packaging?.suppliers?.let { SuppliersSection(it) }
    }
}