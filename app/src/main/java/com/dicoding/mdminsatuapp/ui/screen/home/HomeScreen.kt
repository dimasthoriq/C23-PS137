package com.dicoding.mdminsatuapp.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.data.local.PreferenceUtils
import com.dicoding.mdminsatuapp.maps.LocationViewModel
import com.dicoding.mdminsatuapp.ui.components.*
import getDummyActivityList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    locationViewModel: LocationViewModel,
) {
    val context = LocalContext.current
    val locationName by remember {
        mutableStateOf(PreferenceUtils.getLocationName(context))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = {
                        navController.navigate("maps")
                        locationViewModel.coordinates.value?.let { coordinates ->
                            locationViewModel.setCoordinates(coordinates.first, coordinates.second)
                        }
                    }) {
                        val truncatedLocationName = truncateString(locationName ?: "Nama Lokasi", 30)
                        Text(
                            text = truncatedLocationName,
                            color = Color.Black,
                            style = MaterialTheme.typography.h6.copy(fontSize = 16.sp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("bucket_list")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bucket),
                            contentDescription = "Bucket List",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFDE59), Color(0xFFFFA91A)),
                        startY = 0f,
                        endY = 200f
                    )
                )
            )
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFDE59), Color(0xFFFFA91A)),
                            startY = 0f,
                            endY = 200f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp))
                    .background(Color.Transparent)
                    .padding(bottom = 20.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    HomeContent(navController = navController)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun truncateString(string: String, maxLength: Int): String {
    return if (string.length > maxLength) {
        string.take(maxLength) + "..."
    } else {
        string
    }
}


val chips = listOf(
    ChipData(R.drawable.ic_travel, "Travel"),
    ChipData(R.drawable.ic_edu, "Edu"),
    ChipData(R.drawable.ic_sports, "Sports"),
    ChipData(R.drawable.ic_art, "Art")
)

@Composable
fun HomeContent(
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier.padding(top = 70.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                CategoryChipsGroup(chips = chips)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recommended Activities",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        items(getDummyActivityList()) { activity ->
            ActivityCard(
                title = activity[0],
                photoUrl = activity[1],
                date = activity[2],
                location = activity[3],
                dateIcon = R.drawable.ic_calendar,
                locationIcon = R.drawable.ic_location,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    val locationViewModel = LocationViewModel()

    HomeScreen(navController = navController, locationViewModel = locationViewModel)
}
