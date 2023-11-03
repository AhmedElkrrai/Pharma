package com.example.pharmamanufacturer.presentation.products.main

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.presentation.products.dialog.ProductionDialog
import com.example.pharmamanufacturer.presentation.theme.DeepBlue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductItem
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun ProductsScreen(
    navController: NavHostController
) {
    val viewModel: ProductsViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsStateWithLifecycle()
    val listener = ProductsScreenListenerImpl(navController, viewModel)

    if (viewState.value.products.isNotEmpty()) {
        LazyColumn {
            val products = viewState.value.products
            items(products) { product ->
                ProductItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    title = product.name,
                    showProductionButton = true,
                    lowStock = product.lowStock,
                    onProductionClick = {
                        listener.showProductionDialog(product)
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

    if (viewState.value.visibleDialog) {
        ProductionDialog(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.4f)
                .border(
                    width = 2.dp,
                    color = DeepBlue,
                    shape = RoundedCornerShape(15.dp)
                ),
            product = viewState.value.selectedProduct!!,
            onDismiss = {
                listener.dismissProductionDialog(navController)
            }
        )
    }
}
