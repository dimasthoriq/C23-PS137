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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.data.local.SessionManager
import com.dicoding.mdminsatuapp.ui.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    sessionManager: SessionManager
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Transparent)
    ) {
        val loginViewModel: LoginViewModel = remember {
            LoginViewModel(sessionManager)
        }

        val emailState = rememberSaveable { mutableStateOf("") }
        val passwordState = rememberSaveable { mutableStateOf("") }
        val emailErrorState = remember { mutableStateOf(false) }
        val passwordErrorState = remember { mutableStateOf(false) }

        val showDialog = remember { mutableStateOf(false) }
        val dialogMessage = remember { mutableStateOf("") }

        val passwordFocusRequester = remember { FocusRequester() }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                    isError = emailErrorState.value,
                    imeAction = ImeAction.Next,
                    onImeAction = { passwordFocusRequester.requestFocus() }
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

                PasswordTextField(
                    password = passwordState.value,
                    onPasswordChange = { passwordState.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester)
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
                                passwordState.value
                            ) { result ->
                                when (result) {
                                    is LoginViewModel.LoginResult.Success -> {
                                        val status = result.status
                                        if (status == 201 || status == 200) {
                                            showDialog.value = true
                                            dialogMessage.value = "Login Success"
                                        } else {
                                            showDialog.value = true
                                            dialogMessage.value = "Login failed. Please try again"
                                        }
                                    }
                                    is LoginViewModel.LoginResult.Error -> {
                                        showDialog.value = true
                                        dialogMessage.value = result.errorMessage
                                    }
                                }
                            }
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

        if (showDialog.value) {
            CustomSuccessDialog(
                showDialog = true,
                onDismiss = {
                    showDialog.value = false
                    navController.navigate("bio")
                },
                message = dialogMessage.value
            )
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = NavController(LocalContext.current),
        sessionManager = SessionManager(context = LocalContext.current)
    )
}