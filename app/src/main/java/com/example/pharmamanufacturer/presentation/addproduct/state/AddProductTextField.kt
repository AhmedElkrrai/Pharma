package com.example.pharmamanufacturer.presentation.addproduct.state

sealed interface AddProductTextField {
    object Name : AddProductTextField

    object CompoundName : AddProductTextField
    object Amount : AddProductTextField
}