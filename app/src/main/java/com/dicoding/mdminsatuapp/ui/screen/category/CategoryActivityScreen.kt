package com.dicoding.mdminsatuapp.ui.screen.category

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.components.ActivityCard
import com.dicoding.mdminsatuapp.ui.components.ActivityCardData
import com.dicoding.mdminsatuapp.ui.components.TopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryActivityScreen(
    navController: NavController,
    category: String) {

    Scaffold(
        topBar = {
            TopBar(
                text = category,
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationIconClick = { navController.navigate("home") }
            )
        },
        content = {
            val activityList = listOf(
                ActivityCardData(
                    title = "Karaoke Night",
                    photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_6.jpg",
                    date = "14 July 2023",
                    location = "TRIBRATA TV, JL. Bulevar Raya, Rukan Graha Bulevar",
                    dateIcon = R.drawable.ic_calendar,
                    locationIcon = R.drawable.ic_location
                ),
                ActivityCardData(
                    title = "Jazzy Night @Shangri-La Jakarta",
                    photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_28.jpg",
                    date = "19 July 2023",
                    location = "Shangri-La Jakarta, Jl. Jenderal Sudirman No.Kav. 1",
                    dateIcon = R.drawable.ic_calendar,
                    locationIcon = R.drawable.ic_location
                ),
                ActivityCardData(
                    title = "Car Cinema Pusenif: My Sassy Girl",
                    photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_29.jpg",
                    date = "21 July 2023",
                    location = "PPI Lapangan Pusenif, Jl. Brigadir Jend. Katamso No.31",
                    dateIcon = R.drawable.ic_calendar,
                    locationIcon = R.drawable.ic_location
                )
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(activityList) { activity ->
                    ActivityCard(
                        title = activity.title,
                        photoUrl = activity.photoUrl,
                        date = activity.date,
                        location = activity.location,
                        dateIcon = activity.dateIcon,
                        locationIcon = activity.locationIcon
                    )
                }

            }
        }
    )



}
