package com.example.pharmamanufacturer.presentation.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen

@Composable
fun DashboardScreen() {
    val viewModel: DashboardViewModel = viewModel()
    val batchesState = viewModel.batchesState.collectAsStateWithLifecycle()

    if (batchesState.value.isNotEmpty()) {
        LazyColumn {
            items(batchesState.value) { batch ->
                BatchItem(batch = batch)
            }
        }
    } else {
        EmptyContentScreen(
            modifier = Modifier.fillMaxSize(),
            message = "Please Start Production..",
            animationResource = R.raw.chemical_reaction
        )
    }
}
