package com.example.pharmamanufacturer.presentation.packaging.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Packaging
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Green
import com.example.pharmamanufacturer.presentation.utilitycompose.DetailsRow

@Composable
fun PackagingItem(
    packaging: Packaging,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        elevation = UiDimensions.Extra_Small,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Small_Space)
        ) {

            if (packaging.lowStock) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = UiDimensions.Small_Space),
                    contentAlignment = Alignment.TopStart
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_warning),
                        contentDescription = "Warning",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            DetailsRow(
                title = packaging.type,
                titleColor = Blue,
                titleStyle = MaterialTheme.typography.titleLarge,
                details = packaging.availableAmount.toString(),
                detailsColor = Green,
                unit = " Unit"
            )
        }
    }
}