package com.example.pharmamanufacturer.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton

@Composable
fun ComponentsScreen(
    onItemClick: (ChemicalComponent) -> Unit,
    onAddClick: () -> Unit
) {
    val viewModel: ComponentsViewModel = viewModel()
    LazyColumn {
        items(viewModel.componentsState.value) { component ->
            ComponentItem(component) {
                onItemClick(component)
            }
        }
    }

    BottomFloatingButton(
        onClick = { onAddClick.invoke() },
        imageVector = Icons.Filled.Add
    )
}
