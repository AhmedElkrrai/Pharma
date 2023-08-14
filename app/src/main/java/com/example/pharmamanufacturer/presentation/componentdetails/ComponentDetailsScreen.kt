package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.presentation.utilitycompose.TopBar

@Composable
fun ComponentDetailsScreen(onBackClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val vm: ComponentDetailsViewModel = viewModel()
        vm.selectedComponent?.let { component ->
            TopBar(
                name = component.name,
                modifier = Modifier.fillMaxWidth(),
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

            AmountDetails(component.amount.round().toString())

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

            if (component.products.isNotEmpty()) {
                ProductsSection(component.products)
            }

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

            component.suppliers?.let { SuppliersSection(it) }
        }
    }
}
