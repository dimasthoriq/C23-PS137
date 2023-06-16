package com.dicoding.mdminsatuapp.ui.screen.activity

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
    val tabTitles = listOf("Upcoming", "Done")
    var selectedTabIndex by mutableStateOf(0)

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
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.Transparent,
                contentColor = MaterialTheme.colors.onPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = MaterialTheme.colors.primary,
                        height = 2.dp
                    )
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title, color = MaterialTheme.colors.secondary) },
                        selectedContentColor = MaterialTheme.colors.secondary
                    )
                }
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
