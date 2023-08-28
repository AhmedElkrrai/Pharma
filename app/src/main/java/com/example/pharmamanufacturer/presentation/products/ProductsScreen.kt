package com.example.pharmamanufacturer.presentation.products

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductItem

@Composable
fun ProductsScreen(
    listener: ProductsScreenListener
) {

    val viewModel: ProductsViewModel = viewModel()
    val productsState = viewModel.productsState.collectAsStateWithLifecycle()

    if (productsState.value.isNotEmpty()) {
        LazyColumn {
            items(productsState.value) { product ->
                ProductItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    title = product.name,
                    showProductionButton = true,
                    lowStock = product.lowStock,
                    onProductionClick = {
                        listener.onProductionStarted(product.id.toString())
                    },
                    onItemClick = {
                        listener.onProductClick(product.id.toString())
                    }
                )
            }
        }
    } else {
        EmptyContentScreen(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            message = "Please add a Product..",
            animationResource = R.raw.cat
        )
    }

    BottomFloatingButton(
        onClick = {
            listener.onAddClick()
        },
        imageVector = Icons.Filled.Add
    )
}
