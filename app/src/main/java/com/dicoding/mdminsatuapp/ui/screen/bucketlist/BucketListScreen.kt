package com.dicoding.mdminsatuapp.ui.screen.bucketlist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.ui.components.ActivityCard
import com.dicoding.mdminsatuapp.ui.components.TopBar
import com.dicoding.mdminsatuapp.R
import getDummyActivityList

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BucketListScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                text = "BucketList",
                navigationIcon = Icons.Default.ArrowBack,
                onNavigationIconClick = { navController.popBackStack() }
            )
        },
        content = {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
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
    )
}

@Preview(showBackground = true)
@Composable
fun BucketListScreenPreview() {
    BucketListScreen(navController = NavController(LocalContext.current))
}
