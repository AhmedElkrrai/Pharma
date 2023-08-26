package com.example.pharmamanufacturer.presentation.utilitycompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.theme.Blue

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Box(
        modifier = modifier.padding(top = UiDimensions.Small_Space),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Blue,
            style = MaterialTheme.typography.titleLarge
        )

        Box(
            modifier = modifier,
            contentAlignment = Alignment.TopStart
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(28.dp)
                    .padding(
                        start = UiDimensions.Small_Space,
                        top = UiDimensions.Small_Space
                    )
                    .clickable {
                        onBackClick.invoke()
                    }
            )
        }

        Box(
            modifier = modifier.padding(end = UiDimensions.Medium_Space),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Edit",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(28.dp)
                    .padding(top = UiDimensions.Small_Space)
                    .clickable {
                        onEditClick.invoke()
                    }
            )
        }
    }
}
