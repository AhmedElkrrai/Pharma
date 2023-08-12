package com.example.pharmamanufacturer.presentation.componentdetails

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
fun AmountDetails(amount: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiDimensions.Medium_Padding)
    ) {
        Text(
            text = "Available:",
            color = Color.DarkGray,
            style = MaterialTheme.typography.titleMedium
        )

        StyledText(amount = amount)
    }
}
