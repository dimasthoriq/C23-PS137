package com.dicoding.mdminsatuapp.ui.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.ui.components.CustomTextField
import com.dicoding.mdminsatuapp.ui.components.PrimaryButton
import com.dicoding.mdminsatuapp.R

@Composable
fun RegisterScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent
            )
    ) {
        val nameState = remember { mutableStateOf("") }
        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        val confirmPasswordState = remember { mutableStateOf("") }
        val nameErrorState = remember { mutableStateOf(false) }
        val emailErrorState = remember { mutableStateOf(false) }
        val passwordErrorState = remember { mutableStateOf(false) }
        val confirmPasswordErrorState = remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_light),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                        Text(
                            text = "MinSatu",
                            style = MaterialTheme.typography.h4,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "REGISTER",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )

                    Text("Name")
                    CustomTextField(
                        value = nameState.value,
                        onValueChange = {
                            nameState.value = it
                            nameErrorState.value = it.isEmpty()
                        },
                        isError = nameErrorState.value
                    )
                    if (nameErrorState.value) {
                        Text(
                            text = "Name cannot be empty",
                            color = Color.Red,
                            style = MaterialTheme.typography.caption
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Email")
                    CustomTextField(
                        value = emailState.value,
                        onValueChange = {
                            emailState.value = it
                            emailErrorState.value = it.isEmpty()
                        },
                        isError = emailErrorState.value
                    )
                    if (emailErrorState.value) {
                        Text(
                            text = "Email cannot be empty",
                            color = Color.Red,
                            style = MaterialTheme.typography.caption
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Password")
                    CustomTextField(
                        value = passwordState.value,
                        onValueChange = {
                            passwordState.value = it
                            passwordErrorState.value = it.isEmpty()
                        },
                        isError = passwordErrorState.value,
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (passwordErrorState.value) {
                        Text(
                            text = "Password cannot be empty",
                            color = Color.Red,
                            style = MaterialTheme.typography.caption
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Confirm Password")
                    CustomTextField(
                        value = confirmPasswordState.value,
                        onValueChange = {
                            confirmPasswordState.value = it
                            confirmPasswordErrorState.value = when {
                                it.isEmpty() -> true
                                it != passwordState.value -> true
                                else -> false
                            }
                        },
                        isError = confirmPasswordErrorState.value,
                        isPassword = true
                    )

                    if (confirmPasswordErrorState.value) {
                        if (confirmPasswordState.value.isEmpty()) {
                            Text(
                                text = "Confirm password cannot be empty",
                                color = Color.Red,
                                style = MaterialTheme.typography.caption
                            )
                        } else {
                            Text(
                                text = "Confirm password must match the password",
                                color = Color.Red,
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    PrimaryButton(
                        text = "REGISTER",
                        onClick = {
                            if (nameState.value.isEmpty()) {
                                nameErrorState.value = true
                            }
                            if (emailState.value.isEmpty()) {
                                emailErrorState.value = true
                            }
                            if (passwordState.value.isEmpty()) {
                                passwordErrorState.value = true
                            }
                            if (confirmPasswordState.value.isEmpty() || confirmPasswordState.value != passwordState.value) {
                                confirmPasswordErrorState.value = true
                            }

                            val allFieldsValid = !nameErrorState.value && !emailErrorState.value && !passwordErrorState.value && !confirmPasswordErrorState.value
                            if (allFieldsValid) {
                                navController.navigate("bio")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        val text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("Already have an Account? ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Login here")
                            }
                        }
                        ClickableText(
                            text = text,
                            onClick = { offset ->
                                if (offset >= "Already have an Account? ".length) {
                                    navController.navigate("login")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}


