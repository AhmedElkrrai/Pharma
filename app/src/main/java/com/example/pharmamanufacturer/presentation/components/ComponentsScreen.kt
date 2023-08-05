package com.example.pharmamanufacturer.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pharmamanufacturer.presentation.Screen

@Composable
fun ComponentsScreen(navController: NavController) {
    val vm: ComponentsViewModel = viewModel()
    LazyColumn {
        items(vm.componentsState) { component ->
            ComponentItem(component) {
                navController.navigate(
                    Screen.ComponentDetailsScreen
                        .withArgs(
                            component.toString()
                        )
                )
            }
        }
    }
}
