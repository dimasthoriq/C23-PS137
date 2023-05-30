package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField() {
    val textState = remember { mutableStateOf("") }

    OutlinedTextField(
        shape = RoundedCornerShape(8.dp),
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        modifier = Modifier
            .height(45.dp)

        )
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CustomTextField()
        }

    }
}