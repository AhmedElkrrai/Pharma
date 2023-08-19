package com.example.pharmamanufacturer.presentation.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductItem

@Composable
fun ComponentsScreen(
    onItemClick: (ChemicalComponent) -> Unit,
    onAddClick: () -> Unit
) {
    val viewModel: ComponentsViewModel = viewModel()
    val componentsState = viewModel.componentsState.collectAsStateWithLifecycle()

    if (componentsState.value.isNotEmpty()) {
        LazyColumn {
            items(componentsState.value) { component ->
                ComponentItem(component) {
                    onItemClick(component)
                }
            }
        }
    } else {
        EmptyContentScreen(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            message = "Please add a Component..",
            animationResource = R.raw.tumbleweed
        )
    }

    BottomFloatingButton(
        onClick = { onAddClick.invoke() },
        imageVector = Icons.Filled.Add
    )
}
