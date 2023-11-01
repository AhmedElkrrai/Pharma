package com.example.pharmamanufacturer.presentation.products.entry

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pharmamanufacturer.R
import com.example.pharmamanufacturer.core.UiDimensions
import com.example.pharmamanufacturer.presentation.products.entry.state.ProductScreenViewState
import com.example.pharmamanufacturer.presentation.products.entry.state.ProductTextField
import com.example.pharmamanufacturer.presentation.theme.Blue
import com.example.pharmamanufacturer.presentation.utilitycompose.BottomFloatingButton
import com.example.pharmamanufacturer.presentation.utilitycompose.CenteredTitleWithIcon
import com.example.pharmamanufacturer.presentation.utilitycompose.textfield.styledTextField

@Composable
fun ProductScreen(
    viewState: ProductScreenViewState,
    listener: ProductScreenListener
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Spacer(modifier = Modifier.height(UiDimensions.Extra_Small))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = "Details",
            painter = painterResource(id = R.drawable.ic_details)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Small_Space))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Small_Space),
            contentAlignment = Alignment.Center
        ) {
            viewState.name.input = styledTextField(
                label = "Name",
                keyboardType = KeyboardType.Text,
                viewState = viewState.name,
                exitErrorState = {
                    listener.exitErrorState(ProductTextField.Name)
                },
                showInvalidInput = {
                    listener.showInvalidInput(ProductTextField.Name)
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Small_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = stringResource(id = R.string.title_compounds),
            painter = painterResource(id = R.drawable.ic_compound)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Small_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Small_Space)
        ) {

            viewState.compoundName.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Small_Space)
                    .weight(0.3f),
                label = "Name",
                keyboardType = KeyboardType.Text,
                viewState = viewState.compoundName,
                exitErrorState = {
                    listener.exitErrorState(ProductTextField.CompoundName)
                },
                showInvalidInput = {
                    listener.showInvalidInput(ProductTextField.CompoundName)
                }
            )

            viewState.concentration.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Small_Space)
                    .weight(0.3f),
                label = "Concentration",
                keyboardType = KeyboardType.Decimal,
                viewState = viewState.concentration,
                exitErrorState = {
                    listener.exitErrorState(ProductTextField.Concentration)
                },
                showInvalidInput = {
                    listener.showInvalidInput(ProductTextField.Concentration)
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Small_Space))

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
                    listener.addCompound()
                }
            ) {
                Text(
                    text = "Add Compound",
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

        Spacer(modifier = Modifier.height(UiDimensions.Large_Space))

        CenteredTitleWithIcon(
            modifier = Modifier.size(30.dp),
            title = stringResource(id = R.string.title_packaging),
            painter = painterResource(id = R.drawable.ic_packaging)
        )

        Spacer(modifier = Modifier.height(UiDimensions.Small_Space))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(UiDimensions.Small_Space)
        ) {

            viewState.packagingType.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Small_Space)
                    .weight(0.3f),
                label = "Type",
                keyboardType = KeyboardType.Ascii,
                viewState = viewState.packagingType,
                exitErrorState = {
                    listener.exitErrorState(ProductTextField.PackagingType)
                },
                showInvalidInput = {
                    listener.showInvalidInput(ProductTextField.PackagingType)
                }
            )

            viewState.packagingAmount.input = styledTextField(
                modifier = Modifier
                    .padding(UiDimensions.Small_Space)
                    .weight(0.3f),
                label = "Amount",
                keyboardType = KeyboardType.Decimal,
                viewState = viewState.packagingAmount,
                exitErrorState = {
                    listener.exitErrorState(ProductTextField.PackagingAmount)
                },
                showInvalidInput = {
                    listener.showInvalidInput(ProductTextField.PackagingAmount)
                }
            )
        }

        Spacer(modifier = Modifier.height(UiDimensions.Small_Space))

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
                    listener.addPackaging()
                }
            ) {
                Text(
                    text = "Add Packaging",
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

        BottomFloatingButton(
            onClick = {
                listener.addProduct()
            }
        )
    }
}
