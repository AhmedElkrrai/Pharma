package com.example.pharmamanufacturer.presentation.compounds.main

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import androidx.compose.foundation.lazy.items

@Composable
fun CompoundsScreen(
    compounds: List<Compound>,
    listener: CompoundsScreenListener
) {
    if (compounds.isNotEmpty()) {
        LazyColumn {
            items(compounds) { compound ->
                CompoundItem(compound) {
                    listener.onCompoundClick(compound.id.toString())
                }
            }
        }
    } else {
        EmptyContentScreen(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            message = "Please add a Compound..",
            animationResource = R.raw.tumbleweed
        )
    }

    BottomFloatingButton(
        onClick = {
            listener.onAddClick()
        },
        imageVector = Icons.Filled.Add
    )
}
