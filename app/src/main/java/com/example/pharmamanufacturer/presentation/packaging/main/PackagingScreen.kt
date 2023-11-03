package com.example.pharmamanufacturer.presentation.packaging.main

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.data.local.entities.Packaging
import com.example.pharmamanufacturer.presentation.products.main.ProductsScreenListener
import com.example.pharmamanufacturer.presentation.products.main.ProductsScreenViewState
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PackagingScreen(
    packagingList: List<Packaging>,
    listener: PackagingScreenListener
) {
    if (packagingList.isNotEmpty()) {
        LazyColumn {
            items(packagingList) { packaging ->
                PackagingItem(packaging) {
                    listener.onPackagingClick(packaging.type)
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
