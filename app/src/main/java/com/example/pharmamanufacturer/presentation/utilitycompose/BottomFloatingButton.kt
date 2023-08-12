package com.example.pharmamanufacturer.presentation.utilitycompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.presentation.theme.Blue

@Composable
fun BottomFloatingButton(
    onClick: () -> Unit,
    color: Color = Blue,
    imageVector: ImageVector = Icons.Filled.Check
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 2.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        FloatingActionButton(
            onClick = { onClick.invoke() },
            backgroundColor = color,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
        }
    }
}
