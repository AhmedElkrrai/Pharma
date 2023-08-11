package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.presentation.UiDimensions

@Composable
fun ComponentDetailsScreen(onBackClick: () -> Unit) {
    val vm: ComponentDetailsViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        vm.selectedComponent?.let { component ->
            TopBar(
                name = component.name,
                modifier = Modifier.fillMaxWidth(),
                onBackClick = onBackClick
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

            ComponentAmountRow(component.amount.toString())
        }
    }
}
