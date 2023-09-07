package com.example.pharmamanufacturer.presentation.products.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductionDialogButton(
    modifier: Modifier = Modifier,
    borderColor: Color,
    buttonMessage: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(40.dp)
            .width(100.dp)
            .border(
                width = 3.dp,
                color = borderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = Color.Black,
            text = buttonMessage,
            fontSize = 16.sp,
            style = MaterialTheme.typography.displayMedium,
        )
    }
}
