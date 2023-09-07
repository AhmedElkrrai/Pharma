package com.example.pharmamanufacturer.presentation.products.dialog

import com.example.pharmamanufacturer.data.local.entities.Batch
import com.example.pharmamanufacturer.data.local.entities.Product

sealed interface ProductionDialogAction {
    data class Operate(
        val batch: Batch,
        val product: Product,
        val onDismiss: () -> Unit
    ) : ProductionDialogAction

    object INVALID : ProductionDialogAction
    object RetrieveInitialState : ProductionDialogAction
}
