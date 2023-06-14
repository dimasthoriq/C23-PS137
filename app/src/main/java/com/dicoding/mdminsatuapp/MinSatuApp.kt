package com.dicoding.mdminsatuapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.data.local.SessionManager
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
import com.dicoding.mdminsatuapp.ui.screen.recommended.RecommendedActivityScreen
import com.dicoding.mdminsatuapp.ui.screen.register.RegisterScreen
import com.dicoding.mdminsatuapp.ui.screen.search.SearchScreen

@Composable
fun MinSatuApp(
    sessionManager: SessionManager
) {
    val navController = rememberNavController()
    val locationViewModel = LocationViewModel()
    val coroutineScope = rememberCoroutineScope()


    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen( navController = navController, sessionManager)
        }
        composable("onboarding") {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnboardingScreen(navController = navController,sessionManager)
            }
        }
        composable("login") {
            LoginScreen( navController = navController, sessionManager)
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
            MainScreen(sessionManager)
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
            ProfileScreen(navController = navController, sessionManager)
        }
        composable("bucket_list") {
            BucketListScreen(navController = navController)
        }
        composable("maps") {
            GoogleMapView(locationViewModel = locationViewModel, context = LocalContext.current, scope = coroutineScope)
        }
        composable("recommendation") {
            RecommendedActivityScreen(navController = navController)
        }

    }

}