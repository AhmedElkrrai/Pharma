package com.example.pharmamanufacturer.presentation.utilitycompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.theme.Green

@Composable
fun DetailsRow(
    title: String = "Available:",
    titleColor: Color = Color.DarkGray,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
    details: String,
    detailsColor: Color = Green,
    unit: String = " KG"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiDimensions.Medium_Space)
    ) {
        Text(
            text = title,
            color = titleColor,
            style = titleStyle
        )

        StyledText(
            details = details,
            unit = unit,
            detailsColor = detailsColor
        )
    }
}
