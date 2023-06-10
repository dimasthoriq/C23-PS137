package com.dicoding.mdminsatuapp.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.dummy.getDummyRecommendationList
import com.dicoding.mdminsatuapp.maps.LocationViewModel
import com.dicoding.mdminsatuapp.ui.components.*
import getDummyActivityList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    locationViewModel: LocationViewModel,
) {
    val formattedAddress by locationViewModel.formattedAddress.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = {
                        navController.navigate("maps")
                    }) {
                        Text(text = formattedAddress ?: "Nama Lokasi", color = Color.Black)
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
                elevation = 0.dp
            )
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Surface {
            Column {
                Box(modifier = Modifier.weight(1f)) {
                    HomeContent(navController = navController, formattedAddress)
                }
                Spacer(modifier = Modifier.height(16.dp))
                BottomNavBar(navController = navController)
            }
        }
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
    formattedAddress: String?
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                CategoryChips(chips = chips)
                Text(
                    text = "Activities Near Me",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                RecommendationCardsList(getDummyRecommendationList())
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Recommended Activities",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
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
