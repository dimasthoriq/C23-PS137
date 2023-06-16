package com.dicoding.mdminsatuapp.ui.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.components.PrimaryButton
import com.dicoding.mdminsatuapp.ui.components.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController) {
    var bucketFilled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                text = "Detail Activity",
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationIconClick = { navController.popBackStack()  }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                val imageUrl =
                    "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_4.jpg"

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Activity Image",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )

                    IconButton(onClick = { bucketFilled = !bucketFilled }, modifier = Modifier.align(Alignment.TopEnd)) {
                        Icon(
                            painter = painterResource(id = if (bucketFilled) R.drawable.ic_bucket_selected else R.drawable.ic_bucket_outline),
                            contentDescription = "Bucket Icon",
                            tint = Color.Unspecified
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    content = {
                        Text(
                            text = "Wahooo!!!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(bottom = 2.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End,
                            content = {
                                Text(
                                    text = "50",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFFFA91A),
                                    modifier = Modifier.size(36.dp)
                                )
                                Text(
                                    text = "Kuota Left",
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        )

                    }
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Organized By: ",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Wahooo",
                        textDecoration = TextDecoration.Underline
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Promo family Wahooo Waterworld, Kota Baru Parahyangan",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

//                ScheduleCard()

                Spacer(modifier = Modifier.height(16.dp))

                PrimaryButton(
                    text = "Join Now",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { /* Handle join button click */ }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(navController = NavController(LocalContext.current))
}

@Composable
fun ScheduleCard() {
    // Kode ScheduleCard()
}

@Preview(showBackground = true)
@Composable
fun PreviewScheduleCard() {
    ScheduleCard()
}
