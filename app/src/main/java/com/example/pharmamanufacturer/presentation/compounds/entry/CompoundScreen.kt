package com.example.pharmamanufacturer.presentation.compounds.entry

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType.Companion.Decimal
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.compounds.entry.state.CompoundScreenViewState
import com.example.pharmamanufacturer.presentation.compounds.entry.state.CompoundTextField
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitleWithIcon
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.styledTextField

@Composable
fun CompoundScreen(
    viewState: CompoundScreenViewState,
    listener: CompoundScreenListener
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Details",
            painter = painterResource(id = R.drawable.ic_details)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space)
        ) {
            viewState.name.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Name",
                keyboardType = Text,
                viewState = viewState.name,
                exitErrorState = {
                    listener.exitErrorState(CompoundTextField.Name)
                },
                showInvalidInput = {
                    listener.showInvalidInput(CompoundTextField.Name)
                }
            )

            viewState.amount.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Amount",
                keyboardType = Decimal,
                viewState = viewState.amount,
                exitErrorState = {
                    listener.exitErrorState(CompoundTextField.Amount)
                },
                showInvalidInput = {
                    listener.showInvalidInput(CompoundTextField.Amount)
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Suppliers",
            painter = painterResource(id = R.drawable.ic_supplier)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space)
        ) {

            viewState.supplierName.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Name",
                keyboardType = Text,
                viewState = viewState.supplierName,
                exitErrorState = {
                    listener.exitErrorState(CompoundTextField.SupplierName)
                },
                showInvalidInput = {
                    listener.showInvalidInput(CompoundTextField.SupplierName)
                }
            )

            viewState.`package`.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Package",
                keyboardType = Decimal,
                viewState = viewState.`package`,
                exitErrorState = {
                    listener.exitErrorState(CompoundTextField.Package)
                },
                showInvalidInput = {
                    listener.showInvalidInput(CompoundTextField.Package)
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

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
                    listener.addSupplier()
                }
            ) {
                Text(
                    text = "Add Supplier",
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

        BottomFloatingButton(
            onClick = {
              listener.addCompound()
            }
        )
    }
}
