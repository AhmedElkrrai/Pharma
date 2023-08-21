package com.example.pharmamanufacturer.presentation.addproduct

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
import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductScreenViewState
import com.example.pharmamanufacturer.presentation.addproduct.state.AddProductTextField
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitleWithIcon
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.styledTextField

@Composable
fun AddProductScreen(
    viewState: AddProductScreenViewState,
    exitErrorState: (AddProductTextField) -> Unit,
    showInvalidInput: (AddProductTextField) -> Unit,
    addCompoundOnClick: () -> Unit,
    addProductOnClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Details",
            painter = painterResource(id = R.drawable.ic_details)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space),
            contentAlignment = Alignment.Center
        ) {
            viewState.name.input = styledTextField(
                label = "Name",
                keyboardType = KeyboardType.Text,
                viewState = viewState.name,
                exitErrorState = {
                    exitErrorState(AddProductTextField.Name)
                },
                showInvalidInput = {
                    showInvalidInput(AddProductTextField.Name)
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Compounds",
            painter = painterResource(id = R.drawable.ic_compound)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Medium_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Medium_Space)
        ) {

            viewState.compoundName.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Name",
                keyboardType = KeyboardType.Text,
                viewState = viewState.compoundName,
                exitErrorState = {
                    exitErrorState(AddProductTextField.CompoundName)
                },
                showInvalidInput = {
                    showInvalidInput(AddProductTextField.CompoundName)
                }
            )

            viewState.concentration.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Medium_Space)
                    .weight(0.3f),
                label = "Concentration",
                keyboardType = KeyboardType.Decimal,
                viewState = viewState.concentration,
                exitErrorState = {
                    exitErrorState(AddProductTextField.Concentration)
                },
                showInvalidInput = {
                    showInvalidInput(AddProductTextField.Concentration)
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
                    .width(200.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Blue),
                onClick = {
                    addCompoundOnClick.invoke()
                }
            ) {
                Text(
                    text = "Add Compound",
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

        BottomFloatingButton(
            onClick = {
                addProductOnClick.invoke()
            }
        )
    }
}
