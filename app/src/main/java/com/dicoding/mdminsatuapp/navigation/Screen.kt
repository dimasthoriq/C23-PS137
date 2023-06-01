package com.dicoding.mdminsatuapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Activity : Screen("activity")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object DetailActivity : Screen("activity/{activityId}") {
        fun createRoute(activityId: Long) = "activity/$activityId"
    }
}