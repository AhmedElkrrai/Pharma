package com.example.pharmamanufacturer.presentation.compounds

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
import com.example.pharmamanufacturer.data.local.entities.Compound
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen

@Composable
fun CompoundsScreen(
    listener: CompoundsScreenListenerImpl
) {
    val viewModel: CompoundsViewModel = viewModel()
    val compoundsState = viewModel.compoundsState.collectAsStateWithLifecycle()

    if (compoundsState.value.isNotEmpty()) {
        LazyColumn {
            items(compoundsState.value) { compound ->
                CompoundItem(compound) {
                    listener.onCompoundClick(compound)
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
