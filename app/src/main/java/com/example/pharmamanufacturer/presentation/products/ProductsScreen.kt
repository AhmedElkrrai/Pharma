package com.example.pharmamanufacturer.presentation.products

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.products.dialog.ProductionDialog
import com.example.pharmamanufacturer.presentation.theme.DeepBlue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.EmptyContentScreen
import com.example.pharmamanufacturer.presentation.utilitycompose.ProductItem

@Composable
fun ProductsScreen(
    listener: ProductsScreenListener
) {
    val viewModel: ProductsViewModel = hiltViewModel()
    val productsState = viewModel.productsState.collectAsStateWithLifecycle()

    var selectedProduct by remember { mutableStateOf<Product?>(null) }

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
                        viewModel.showDialog()
                        selectedProduct = product
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

    if (viewModel.isDialogShown) {
        selectedProduct?.let {
            ProductionDialog(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.4f)
                    .border(
                        width = 2.dp,
                        color = DeepBlue,
                        shape = RoundedCornerShape(15.dp)
                    ),
                product = it
            ) {
                viewModel.dismissDialog()
            }
        }
    }
}
