package com.example.pharmamanufacturer.presentation.productdetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.MINIMUM_PRODUCT_BATCHES
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Green
import com.example.pharmamanufacturer.presentation.theme.Red
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitle
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow
import com.example.pharmamanufacturer.presentation.utilitycompose.StyledText

@Composable
fun ProductCompoundItem(
    modifier: Modifier = Modifier,
    name: String,
    availableAmount: Double,
    concentration: Double
) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(10.dp)
    ) {
        var availableBatches = 0.0

        Column {
            CenteredTitle(
                modifier = Modifier.padding(UiDimensions.Small_Space),
                title = name,
                titleColor = Blue
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

            DetailsRow(
                details = availableAmount.toString()
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

            DetailsRow(
                title = "Concentration:",
                details = concentration.toString()
            )

            Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

            availableBatches = (availableAmount / concentration).round()
            val unit = if (availableBatches >= 2) " Batches" else " Batch"
            val detailsColor = if (availableBatches < MINIMUM_PRODUCT_BATCHES) Red else Green

            DetailsRow(
                title = "Batches:",
                details = availableBatches.toString(),
                unit = unit,
                detailsColor = detailsColor
            )
        }

        if (availableBatches < MINIMUM_PRODUCT_BATCHES) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 10.dp,
                    ),
                contentAlignment = Alignment.TopStart
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = "Warning",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
