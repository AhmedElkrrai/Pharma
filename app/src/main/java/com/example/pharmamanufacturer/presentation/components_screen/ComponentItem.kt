package com.example.pharmamanufacturer.presentation.components_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.data.models.ChemicalComponent
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.theme.Green

@Composable
fun ComponentItem(
    component: ChemicalComponent,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {}
) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .padding(10.dp)
            .clickable {
                onClick(component.id)
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = component.name,
                modifier = Modifier.padding(start = 10.dp),
                style = MaterialTheme.typography.titleLarge,
                color = Blue,
            )

            Text(
                text = component.amount.toString(),
                modifier = Modifier.padding(end = 10.dp),
                style = MaterialTheme.typography.titleMedium,
                color = Green
            )
        }
    }
}
