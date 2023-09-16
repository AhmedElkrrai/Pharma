package com.example.pharmamanufacturer.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pharmamanufacturer.data.local.database.DatabaseHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val db: DatabaseHandler
) : ViewModel() {
    private val _batchesState: MutableStateFlow<List<BatchViewState>> =
        MutableStateFlow(emptyList())
    val batchesState: StateFlow<List<BatchViewState>>
        get() = _batchesState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            initBatchesState()
        }
    }

    private suspend fun initBatchesState() {
        val batches = db.getAllBatches()
        val batchesState = mutableListOf<BatchViewState>()
        for (batch in batches) {
            db.getProduct(batch.productId)?.let {
                val batchViewState = BatchViewState(
                    number = batch.number,
                    date = batch.date,
                    product = it
                )
                batchesState.add(batchViewState)
            }
        }

        _batchesState.getAndUpdate { batchesState }
    }
}
