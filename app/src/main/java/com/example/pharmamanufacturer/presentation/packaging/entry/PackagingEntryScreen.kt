package com.example.pharmamanufacturer.presentation.packaging.entry

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.packaging.entry.state.PackagingEntryScreenViewState
import com.example.pharmamanufacturer.presentation.packaging.entry.state.PackagingEntryTextField
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitleWithIcon
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.styledTextField

@Composable
fun PackagingEntryScreen(
    viewState: PackagingEntryScreenViewState,
    listener: PackagingEntryScreenListener
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
            viewState.type.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Type",
                keyboardType = KeyboardType.Text,
                viewState = viewState.type,
                exitErrorState = {
                    listener.exitErrorState(PackagingEntryTextField.Type)
                },
                showInvalidInput = {
                    listener.showInvalidInput(PackagingEntryTextField.Type)
                }
            )

            viewState.amount.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Amount",
                keyboardType = KeyboardType.Decimal,
                viewState = viewState.amount,
                exitErrorState = {
                    listener.exitErrorState(PackagingEntryTextField.Amount)
                },
                showInvalidInput = {
                    listener.showInvalidInput(PackagingEntryTextField.Amount)
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
                keyboardType = KeyboardType.Text,
                viewState = viewState.supplierName,
                exitErrorState = {
                    listener.exitErrorState(PackagingEntryTextField.SupplierName)
                },
                showInvalidInput = {
                    listener.showInvalidInput(PackagingEntryTextField.SupplierName)
                }
            )

            viewState.`package`.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Package",
                keyboardType = KeyboardType.Decimal,
                viewState = viewState.`package`,
                exitErrorState = {
                    listener.exitErrorState(PackagingEntryTextField.Package)
                },
                showInvalidInput = {
                    listener.showInvalidInput(PackagingEntryTextField.Package)
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
                listener.updatePackaging()
            }
        )
    }
}