package com.example.pharmamanufacturer.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.data.models.BottomMenuItem
import com.example.pharmamanufacturer.ui.compose.theme.DeepBlue

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        BottomMenu(
            items = listOf(
                BottomMenuItem(
                    stringResource(id = R.string.title_products),
                    R.drawable.health_icon
                ),
                BottomMenuItem(
                    stringResource(id = R.string.title_dashboard),
                    R.drawable.doctor_icon
                ),
                BottomMenuItem(
                    stringResource(id = R.string.title_components),
                    R.drawable.ic_medicine_component
                ),
                BottomMenuItem(
                    stringResource(id = R.string.title_packing),
                    R.drawable.ic_packing
                ),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuItem>,
    modifier: Modifier = Modifier,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            MenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun MenuItem(
    item: BottomMenuItem,
    isSelected: Boolean = false,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) item.activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) item.activeColor else item.inactiveColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}