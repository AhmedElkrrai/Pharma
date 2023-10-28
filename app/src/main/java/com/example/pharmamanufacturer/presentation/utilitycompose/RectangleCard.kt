package com.example.pharmamanufacturer.presentation.utilitycompose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.core.UiDimensions

@Composable
fun RectangleCard(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = Color.DarkGray,
    onItemClick: (() -> Unit)? = null
) {
    Card(
        elevation = UiDimensions.Medium_Space,
        shape = RectangleShape,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.DarkGray,
            )
            .clickable { onItemClick?.invoke() }
    ) {
        Box(
            modifier = modifier
                .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = titleColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
