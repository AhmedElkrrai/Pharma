package com.example.pharmamanufacturer.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.presentation.theme.Blue

@Composable
fun ComponentsScreen(onItemClick: (ChemicalComponent) -> Unit) {
    val viewModel: ComponentsViewModel = viewModel()
    LazyColumn {
        items(viewModel.componentsState.value) { component ->
            ComponentItem(component) {
                onItemClick(component)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 2.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        FloatingActionButton(
            onClick = {
                val component = ChemicalComponent(
                    id = 1222,
                    name = "BatMan",
                    amount = 12.0
                )
                viewModel.addChemicalComponent(component)
            },
            backgroundColor = Blue,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }
}
