package com.example.pharmamanufacturer.presentation.addcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Decimal
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.core.round
import com.example.pharmamanufacturer.data.local.entities.ChemicalComponent
import com.example.pharmamanufacturer.data.local.entities.Supplier
import com.example.pharmamanufacturer.presentation.theme.AquaBlue
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitleWithIcon
import com.example.pharmamanufacturer.presentation.utilitycompose.styledTextField

@Composable
fun AddComponentScreen(navigateBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val viewModel: AddComponentViewModel = viewModel()

        var name by remember { mutableStateOf("") }
        var amount by remember { mutableStateOf("") }
        var suppliers by remember { mutableStateOf(listOf<Supplier>()) }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Details",
            painter = painterResource(id = R.drawable.ic_details)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Padding)
        ) {

            name = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Padding)
                    .weight(0.3f),
                placeHolderText = "Name",
                label = "Name",
                keyboardType = Text
            )

            amount = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Padding)
                    .weight(0.3f),
                placeHolderText = "Available Amount",
                label = "Amount",
                keyboardType = Decimal
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Suppliers",
            painter = painterResource(id = R.drawable.ic_doctor)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Padding))

        Column(modifier = Modifier.fillMaxWidth()) {
            var supplierName by remember { mutableStateOf("") }
            var capacity by remember { mutableStateOf("") }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UiDimensions.Medium_Padding)
            ) {
                supplierName = styledTextField(
                    modifier = Modifier
                        .padding(UiDimensions.Medium_Padding)
                        .weight(0.3f),
                    placeHolderText = "Name",
                    label = "Name",
                    keyboardType = Text
                )

                capacity = styledTextField(
                    modifier = Modifier
                        .padding(UiDimensions.Medium_Padding)
                        .weight(0.3f),
                    placeHolderText = "Capacity",
                    label = "Capacity",
                    keyboardType = Decimal
                )
            }

            Spacer(modifier = Modifier.height(UiDimensions.Small_Padding))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
                    onClick = {
                        suppliers = suppliers + listOf(
                            Supplier(
                                name = supplierName,
                                capacity = capacity.toDouble().round()
                            )
                        )
                    }
                ) {
                    Text(
                        text = "Add Supplier",
                        color = AquaBlue,
                        maxLines = 1
                    )
                }
            }
        }

        BottomFloatingButton(
            onClick = {
                val component = ChemicalComponent(
                    name = name,
                    amount = amount.toDouble(),
                    products = listOf("Wottah", "poison"),
                    suppliers = suppliers
                )
                viewModel.addComponent(component)
                navigateBack.invoke()
            }
        )
    }
}
