package com.dicoding.mdminsatuapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.data.local.SessionManager
import com.dicoding.mdminsatuapp.maps.LocationViewModel
import com.dicoding.mdminsatuapp.navigation.Screen
import com.dicoding.mdminsatuapp.ui.components.BottomNavBar
import com.dicoding.mdminsatuapp.ui.screen.activity.ActivityScreen
import com.dicoding.mdminsatuapp.ui.screen.home.HomeScreen
import com.dicoding.mdminsatuapp.ui.screen.profile.ProfileScreen

@Composable
fun MainScreen(sessionManager: SessionManager) {
    val navController = rememberNavController()

    LaunchedEffect(key1 = true) {
        val userId = sessionManager.getUserId()
        if (userId != null) {
            navController.navigate("home")
        } else {
            navController.navigate("onboarding")
        }
    }

    val locationViewModel = LocationViewModel()

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                BottomNavBar(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController, locationViewModel)
            }
            composable(Screen.Activity.route) {
                ActivityScreen(navController)
            }

            composable(Screen.Profile.route) {
                ProfileScreen(navController,sessionManager)
            }
        }
    }
}
