package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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

@Composable
fun LabelTextField(
    label: String,
    placeholder: String,
    isTextArea: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(if (isTextArea) 100.dp else 80.dp)
            .padding(vertical = 8.dp),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        maxLines = if (isTextArea) 4 else 1,
        shape = RoundedCornerShape(4.dp)
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