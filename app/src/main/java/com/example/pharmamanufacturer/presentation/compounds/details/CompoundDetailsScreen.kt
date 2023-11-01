package com.example.pharmamanufacturer.presentation.compounds.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.Screen
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductsSection
import com.example.pharmamanufacturer.presentation.utilitycompose.SuppliersSection
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun CompoundDetailsScreen(
    compoundState: State<Compound?>,
    productsState: State<List<Product>>,
    listener: CompoundDetailsScreenListener
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            name = compoundState.value?.name ?: "",
            modifier = Modifier.fillMaxWidth(),
            onBackClick = { listener.navigateBack() },
            onEditClick = {
                listener.onEditClick(
                    compoundId = compoundState.value?.id.toString(),
                    compoundName = compoundState.value?.name ?: ""
                )
            }
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        DetailsRow(
            details = compoundState.value?.availableAmount?.round().toString()
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (compoundState.value?.productNodes?.isNotEmpty() == true) {
            val modifier = if (productsState.value.size < 3)
                Modifier.fillMaxWidth()
            else Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)

            compoundState.value?.let { compound ->
                ProductsSection(
                    modifier = modifier,
                    materialId = compound.id!!,
                    availableAmount = compound.availableAmount,
                    products = productsState.value,
                    screen = Screen.CompoundDetailsScreen
                )
            }
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        if (compoundState.value?.suppliers.isNullOrEmpty()) {
            EmptyContentScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                message = "Please Add a Supplier..",
                animationResource = R.raw.tumbleweed
            )
        } else
            compoundState.value?.suppliers?.let { SuppliersSection(it) }
    }
}
