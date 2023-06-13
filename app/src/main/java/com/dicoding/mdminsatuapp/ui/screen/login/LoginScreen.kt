package com.dicoding.mdminsatuapp.ui.screen.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.components.CustomErrorDialog
import com.dicoding.mdminsatuapp.ui.components.CustomSuccessDialog
import com.dicoding.mdminsatuapp.ui.components.CustomTextField
import com.dicoding.mdminsatuapp.ui.components.PrimaryButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
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
        val loginViewModel: LoginViewModel = viewModel()

        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        val emailErrorState = remember { mutableStateOf(false) }
        val passwordErrorState = remember { mutableStateOf(false) }

        val showDialog = remember { mutableStateOf(false) }
        val dialogTitle = remember { mutableStateOf("") }
        val dialogMessage = remember { mutableStateOf("") }
        val showSuccessDialog = remember { mutableStateOf(false) }

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
                        text = "LOGIN",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )

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
                        onValueChange = { passwordState.value = it },
                        isError = passwordErrorState.value,
                        isPassword = true
                    )

                    if (passwordErrorState.value) {
                        Text(
                            text = "Password cannot be empty",
                            color = Color.Red,
                            style = MaterialTheme.typography.caption
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    PrimaryButton(
                        text = "LOGIN",
                        onClick = {
                            if (emailState.value.isEmpty()) {
                                emailErrorState.value = true
                            }
                            if (passwordState.value.isEmpty()) {
                                passwordErrorState.value = true
                            }
                            if (!emailErrorState.value && !passwordErrorState.value) {
                                loginViewModel.loginUser(
                                    emailState.value,
                                    passwordState.value,
                                    onSuccess = {
                                        dialogTitle.value = "Success"
                                        dialogMessage.value =
                                            "Login Success"
                                        showDialog.value = true
                                        showSuccessDialog.value = true

                                        if (showSuccessDialog.value) {
                                            navController.navigate("home")
                                        }

                                    },
                                    onError = { errorMessage ->
                                        dialogTitle.value = "Login Failed"
                                        dialogMessage.value = errorMessage
                                        showDialog.value = true
                                    }
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        val text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("Don't have an Account? ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Blue,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("Register here")
                            }
                        }
                        ClickableText(
                            text = text,
                            onClick = { offset ->
                                if (offset >= "Don't have an Account? ".length) {
                                    navController.navigate("register")
                                }
                            }
                        )
                    }
                }
            }
        }

        if (showDialog.value) {
            if (showSuccessDialog.value) {
                CustomSuccessDialog(
                    showDialog = true,
                    onDismiss = {
                        showDialog.value = false
                    },
                    title = dialogTitle.value,
                    message = dialogMessage.value,

                )
            } else {
                CustomErrorDialog(
                    showDialog = true,
                    onDismiss = { showDialog.value = false },
                    title = dialogTitle.value,
                    message = dialogMessage.value
                )
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}
