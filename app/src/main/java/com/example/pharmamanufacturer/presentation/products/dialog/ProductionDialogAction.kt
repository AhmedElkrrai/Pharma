package com.example.pharmamanufacturer.presentation.products.dialog

import com.example.pharmamanufacturer.data.local.entities.Batch

sealed interface ProductionDialogAction {
    data class Operate(val batch: Batch) : ProductionDialogAction
    object INVALID : ProductionDialogAction
    object RetrieveInitialState : ProductionDialogAction
}
