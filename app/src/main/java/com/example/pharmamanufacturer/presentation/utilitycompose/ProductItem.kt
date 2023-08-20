package com.example.pharmamanufacturer.presentation.utilitycompose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.data.local.entities.Product
import com.example.pharmamanufacturer.presentation.theme.Blue

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onItemClick: (() -> Unit)? = null
) {
    Card(
        elevation = 2.dp,
        shape = RectangleShape,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
            )
            .clickable { onItemClick?.invoke() }
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = product.name,
                color = Blue,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = "Warning",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(28.dp)
                        .padding(start = UiDimensions.Small_Space)
                )
            }
        }
    }
}
