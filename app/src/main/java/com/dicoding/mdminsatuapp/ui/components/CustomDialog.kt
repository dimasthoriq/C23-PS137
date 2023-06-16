package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dicoding.mdminsatuapp.R

@Composable
fun CustomSuccessDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String = "Success",
    message: String = "You successfully joined an activity, please don’t forget to come to the site."
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(16.dp)
                        .fillMaxWidth(.85f)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_success),
                            contentDescription = "Success",
                            modifier = Modifier.size(100.dp)
                        )
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = message,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                        OutlinedButton(
                            onClick = onDismiss,
                            border = BorderStroke(2.dp, Color.Green),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Green),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(42.dp, 4.dp),
                        ) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CustomErrorDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String = "Error",
    message: String = "An error occurred. Please try again later."
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(16.dp)
                        .fillMaxWidth(.85f)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_error),
                            contentDescription = "Error",
                            modifier = Modifier.size(100.dp)
                        )
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = message,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                        OutlinedButton(
                            onClick = onDismiss,
                            border = BorderStroke(2.dp, Color.Red),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(42.dp, 4.dp)
                        ) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DialogPreview() {

    CustomSuccessDialog(
        showDialog = true,
        onDismiss = {},
        title = "Success",
        message = "Login Success"
    )

//    CustomErrorDialog(
//        showDialog = true,
//        onDismiss = { /* action when dismissed */ },
//        title = "Login Failed",
//        message = "email or password is incorrect"
//    )
}