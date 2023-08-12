package com.example.pharmamanufacturer.presentation.addcomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.theme.Blue

@Composable
fun AddComponentScreen() {
    val viewModel: AddComponentViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 2.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        FloatingActionButton(
            onClick = {
                val component = ChemicalComponent(
                    id = 12,
                    name = "BatMan",
                    amount = 12.0,
                    products = listOf("Watah", "poison"),
                    suppliers = listOf(
                        Supplier("AbouTricka", 22.0),
                        Supplier("Kaka", 17.0),
                        Supplier("Super Mario", 32.0),
                    )
                )
                viewModel.addComponent(component)
            },
            backgroundColor = Blue,
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null
            )
        }
    }
}
