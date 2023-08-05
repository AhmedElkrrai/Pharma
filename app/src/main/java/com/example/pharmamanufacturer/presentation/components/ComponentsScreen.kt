package com.example.pharmamanufacturer.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.data.models.ChemicalComponent

@Composable
fun ComponentsScreen(onItemClick: (ChemicalComponent) -> Unit) {
    val vm: ComponentsViewModel = viewModel()
    LazyColumn {
        items(vm.componentsState) { component ->
            ComponentItem(component) {
                onItemClick(component)
            }
        }
    }
}
