package com.example.pharmamanufacturer.presentation.packaging

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.compounds.CompoundItem
import com.example.pharmamanufacturer.presentation.compounds.CompoundsViewModel
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen

@Composable
fun PackagingScreen() {
    val viewModel: PackagingViewModel = hiltViewModel()
    val packagingState = viewModel.packagingState.collectAsStateWithLifecycle()

    if (packagingState.value.isNotEmpty()) {
        LazyColumn {
            items(packagingState.value) { packaging ->
                PackagingItem(packaging) {

                }
            }
        }
    } else {
        EmptyContentScreen(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            message = "Please Add Packaging to Product(s)..",
            animationResource = R.raw.waiting
        )
    }
}
