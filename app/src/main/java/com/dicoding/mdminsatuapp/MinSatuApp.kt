package com.dicoding.mdminsatuapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.maps.GoogleMapView
import com.dicoding.mdminsatuapp.maps.LocationViewModel
import com.dicoding.mdminsatuapp.ui.screen.MainScreen
import com.dicoding.mdminsatuapp.ui.screen.OnboardingScreen
import com.dicoding.mdminsatuapp.ui.screen.SplashScreen
import com.dicoding.mdminsatuapp.ui.screen.activity.ActivityScreen
import com.dicoding.mdminsatuapp.ui.screen.bucketlist.BucketListScreen
import com.dicoding.mdminsatuapp.ui.screen.home.HomeScreen
import com.dicoding.mdminsatuapp.ui.screen.login.LoginScreen
import com.dicoding.mdminsatuapp.ui.screen.profile.ProfileScreen
import com.dicoding.mdminsatuapp.ui.screen.quicksurvey.QuickSurveyBioScreen
import com.dicoding.mdminsatuapp.ui.screen.quicksurvey.QuickSurveyInterestScreen
import com.dicoding.mdminsatuapp.ui.screen.register.RegisterScreen
import com.dicoding.mdminsatuapp.ui.screen.search.SearchScreen

@Composable
fun MinSatuApp() {
    val navController = rememberNavController()
    val locationViewModel = LocationViewModel()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen( navController = navController)
        }
        composable("onboarding") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnboardingScreen(navController = navController)
            }
        }
        composable("login") {
            LoginScreen( navController = navController)
        }
        composable("register") {
            RegisterScreen( navController = navController)
        }
        composable("bio") {
            QuickSurveyBioScreen(navController = navController)
        }
        composable("interest") {
            QuickSurveyInterestScreen(navController = navController)
        }
        composable("main_screen") {
            MainScreen()
        }
        composable("home") {
            HomeScreen(navController = navController,locationViewModel)
        }
        composable("activity") {
            ActivityScreen(navController = navController)
        }
        composable("search") {
            SearchScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("bucket_list") {
            BucketListScreen(navController = navController)
        }
        composable("maps") {
            GoogleMapView()
        }

    }

}