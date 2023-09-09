package com.example.pharmamanufacturer.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow

@Composable
fun BatchItem(
    modifier: Modifier = Modifier,
    batch: BatchViewState
) {
    Card(
        elevation = UiDimensions.Elevation,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.padding(10.dp)
    ) {
        Column {
            CenteredTitle(
                modifier = Modifier.padding(UiDimensions.Small_Space),
                title = batch.product.name,
                titleColor = Blue
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

            DetailsRow(
                title = batch.date,
                titleColor = Blue,
                details = batch.number,
                unit = ""
            )
        }
    }
}