package com.dicoding.mdminsatuapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.SessionManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, sessionManager: SessionManager) {
    LaunchedEffect(key1 = true) {
        delay(5000)
        val userId = sessionManager.getUserId()
        if (userId != null && userId.isNotEmpty()) {
            navController.navigate("home")
        } else {
            navController.navigate("onboarding")
        }

    }

    val gradientColorList = listOf(
        Color(0xFFFFF176),
        Color(0xFFFFEE58),
        Color(0xFFFFEB38),
        Color(0xFFFFD600),
        Color(0xFFFFC107),

        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = GradientBackground(
                    isVerticalGradient = false,
                    colors = gradientColorList
                )),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_dark),
            contentDescription = "logo_dark", modifier = Modifier.width(150.dp).height(150.dp)
        )

    }


}

@Composable
private fun GradientBackground(
    isVerticalGradient: Boolean,
    colors: List<Color>
): Brush {
    val endOffset = if(isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )

}

