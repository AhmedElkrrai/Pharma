package com.example.pharmamanufacturer.presentaion.components_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.presentaion.theme.ComposeTheme

@Composable
fun ComponentsScreen() {
    val vm: ComponentsViewModel = viewModel()
    LazyColumn {
        items(vm.componentsState) { components ->
            ComponentItem(components) {
                vm.updateAmount(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsScreenPreview() {
    ComposeTheme {
        ComponentsScreen()
    }
}
