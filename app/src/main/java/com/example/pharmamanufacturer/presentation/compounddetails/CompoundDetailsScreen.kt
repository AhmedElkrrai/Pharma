package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun CompoundDetailsScreen(onBackClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val viewModel: CompoundDetailsViewModel = viewModel()
        viewModel.selectedCompound?.let { compound ->
            TopBar(
                name = compound.name,
                modifier = Modifier.fillMaxWidth(),
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

            DetailsRow(
                details = compound.availableAmount.round().toString()
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

            if (compound.batches?.isNotEmpty() == true) {
                val productsState =
                    viewModel
                        .getCompoundProducts(compound.batches.map { it.id })
                        .collectAsStateWithLifecycle()

                ProductsSection(
                    compound = compound,
                    products = productsState.value
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
}
