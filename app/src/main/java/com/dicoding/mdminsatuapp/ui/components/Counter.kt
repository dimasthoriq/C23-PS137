package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterTextField(age: Int, onAgeChange: (Int) -> Unit) {
    val textColor = Color(0xFF000000)
    val focusRequester = remember { FocusRequester() }

    val textFieldValue = remember { mutableStateOf(TextFieldValue(text = age.toString())) }

    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = textFieldValue.value,
            onValueChange = { value ->
                textFieldValue.value = value.copy(text = value.text.filter { it.isDigit() })
                val newAge = value.text.toIntOrNull() ?: 0
                onAgeChange(newAge)
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .padding(top = 8.dp, bottom = 4.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontSize = 16.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CounterTextFieldPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        val ageState = remember { mutableStateOf(0) }
        CounterTextField(age = ageState.value) { newAge ->
            ageState.value = newAge
        }
        Text(
            text = "Age: ${ageState.value}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
