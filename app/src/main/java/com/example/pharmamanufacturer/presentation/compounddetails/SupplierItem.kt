package com.example.pharmamanufacturer.presentation.compounddetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle
import com.example.pharmamanufacturer.presentation.utilitycompose.StyledText

@Composable
fun SupplierItem(
    modifier: Modifier = Modifier,
    supplier: Supplier
) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(10.dp)
            .height(80.dp)
    ) {
        Column {
            CenteredTitle(
                modifier = Modifier.padding(UiDimensions.Small_Space),
                title = supplier.name,
                titleColor = Blue
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                StyledText(
                    modifier = Modifier.padding(bottom = UiDimensions.Small_Space),
                    details = supplier.`package`.round().toString()
                )
            }
        }
    }
}
