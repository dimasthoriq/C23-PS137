package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mdminsatuapp.R

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    isPassword: Boolean = false,
    imeAction: ImeAction = ImeAction.Default,
    onImeAction: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        shape = RoundedCornerShape(8.dp),
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(45.dp)
            .fillMaxWidth(),
        isError = isError,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
        keyboardActions = KeyboardActions(onNext = { onImeAction() })
    )
}






@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = modifier
            .height(45.dp)
            .fillMaxWidth(),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(id = if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visible),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
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
           // CustomTextField()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            PasswordTextField(
                password = "",
                onPasswordChange = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
