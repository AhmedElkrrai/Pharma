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
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.utilitycompose.StyledText

@Composable
fun DetailsRow(
    title: String = "Available:",
    details: String,
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
            color = Color.DarkGray,
            style = MaterialTheme.typography.titleMedium
        )

        StyledText(
            details = details,
            unit = unit
        )
    }
}
