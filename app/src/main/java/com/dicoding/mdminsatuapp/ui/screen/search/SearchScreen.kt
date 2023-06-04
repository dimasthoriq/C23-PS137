package com.dicoding.mdminsatuapp.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.ui.components.BottomNavBar
import com.dicoding.mdminsatuapp.ui.components.FilterChipsGroup
import com.dicoding.mdminsatuapp.ui.components.SearchBar

@Composable
fun SearchScreen(navController: NavController) {
    Column {
        SearchBar()
        Column(modifier = Modifier.weight(1f)) {
            FilterChipsGroup()
        }
        BottomNavBar(navController = navController)
    }
}


@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(navController = NavController(LocalContext.current))
}