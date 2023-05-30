package com.dicoding.mdminsatuapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.ui.screen.OnboardingScreen
import com.dicoding.mdminsatuapp.ui.screen.SplashScreen
import com.dicoding.mdminsatuapp.ui.screen.login.LoginScreen
import com.dicoding.mdminsatuapp.ui.screen.register.RegisterScreen

@Composable
fun MinSatuApp() {
    val navController = rememberNavController()
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

    }

}