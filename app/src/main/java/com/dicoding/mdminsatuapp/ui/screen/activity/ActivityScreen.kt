package com.dicoding.mdminsatuapp.ui.screen.activity

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.ui.components.ActivityCard
import com.dicoding.mdminsatuapp.ui.components.BottomNavBar
import com.dicoding.mdminsatuapp.ui.components.TopBar
import com.dicoding.mdminsatuapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ActivityScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(text = "Activity Status")
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column {
            TabRow(
                selectedTabIndex = 0,
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colors.onPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[0]),
                        color = MaterialTheme.colors.primary,
                        height = 2.dp
                    )
                }
            ) {
                Tab(
                    selected = true,
                    onClick = { },
                    text = { Text("Upcoming", color = MaterialTheme.colors.primary) },
                    selectedContentColor = MaterialTheme.colors.primary
                )
                Tab(
                    selected = false,
                    onClick = { },
                    text = { Text("Done", color = MaterialTheme.colors.primary) },
                    selectedContentColor = MaterialTheme.colors.primary
                )
            }
            LazyColumn {
                items(10) { index ->
                    ActivityCard(
                        title = "Activity $index",
                        photoUrl = "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
                        date = "Date",
                        location = "Location",
                        dateIcon = R.drawable.ic_calendar,
                        locationIcon = R.drawable.ic_location,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewActivityScreen() {
    val navController = rememberNavController()
    ActivityScreen(navController = navController)
}