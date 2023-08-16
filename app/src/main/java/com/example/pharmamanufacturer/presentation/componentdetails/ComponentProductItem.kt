package com.example.pharmamanufacturer.presentation.componentdetails

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import com.example.pharmamanufacturer.presentation.theme.Blue

@Composable
fun ComponentProductItem(
    modifier: Modifier = Modifier,
    name: String
) {
    Card(
        elevation = 2.dp,
        shape = RectangleShape,
        modifier = modifier
            .padding(10.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
            )
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                color = Blue,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = modifier,
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
