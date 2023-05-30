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
                    CustomTextField()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Email")
                    CustomTextField()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Password")
                    CustomTextField()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Confirm Password")
                    CustomTextField()
                    Spacer(modifier = Modifier.height(20.dp))
                    PrimaryButton(
                        text = "REGISTER",
                        onClick = {},
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
