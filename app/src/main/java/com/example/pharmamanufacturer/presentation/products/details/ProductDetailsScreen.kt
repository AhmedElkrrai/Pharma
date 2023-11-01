package com.example.pharmamanufacturer.presentation.products.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_BATCHES
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.theme.Green
import com.example.pharmamanufacturer.presentation.theme.Orange
import com.example.pharmamanufacturer.presentation.theme.Red
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.RectangleCard
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun ProductDetailsScreen(
    viewState: ProductDetailsViewState,
    listener: ProductDetailsScreenListener
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = viewState.product?.name ?: return,
            modifier = Modifier.fillMaxWidth(),
            onBackClick = { listener.navigateBack() },
            onEditClick = {
                listener.onEditClick(
                    productId = viewState.product.id.toString(),
                    productName = viewState.product.name
                )
            }
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        val availableBatches = viewState.product.getAvailableBatches()
        val unit = if (availableBatches >= 2) " Batches" else " Batch"
        val detailsColor = if (availableBatches < MINIMUM_PRODUCT_BATCHES) Red else Green

        DetailsRow(
            details = availableBatches.toString(),
            unit = unit,
            detailsColor = detailsColor
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space)
        ) {
            RectangleCard(
                title = stringResource(id = R.string.title_compounds),
                titleColor =
                if (viewState.selectedTab == ProductDetailsTab.COMPOUNDS)
                    Orange
                else Color.DarkGray,
                onItemClick = {
                    listener.onTabSelected(ProductDetailsTab.COMPOUNDS)
                }
            )

            RectangleCard(
                title = stringResource(id = R.string.title_packaging),
                titleColor =
                if (viewState.selectedTab == ProductDetailsTab.PACKAGING)
                    Orange
                else Color.DarkGray,
                onItemClick = {
                    listener.onTabSelected(ProductDetailsTab.PACKAGING)
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (viewState.selectedTab == ProductDetailsTab.COMPOUNDS) {
            LazyColumn {
                items(
                    viewState.compounds
                        .sortedBy { !it.lowStock }
                ) { compound ->
                    val concentration =
                        viewState.product.compoundNodes
                            .find { it.id == compound.id }?.neededAmount ?: 0.0

                    ProductCompoundItem(
                        name = compound.name,
                        availableAmount = compound.availableAmount,
                        neededAmount = concentration,
                        selectedTab = ProductDetailsTab.COMPOUNDS
                    )
                }
            }
        }

        if (viewState.selectedTab == ProductDetailsTab.PACKAGING) {
            LazyColumn {
                items(
                    viewState.packagingList
                        .sortedBy { !it.lowStock }
                ) { packaging ->
                    val neededAmount =
                        viewState.product.packagingNodes
                            .find { it.id == packaging.id }?.neededAmount ?: 0.0

                    ProductCompoundItem(
                        name = packaging.type,
                        availableAmount = packaging.availableAmount,
                        neededAmount = neededAmount,
                        selectedTab = ProductDetailsTab.PACKAGING
                    )
                }
            }
        }
    }
}
