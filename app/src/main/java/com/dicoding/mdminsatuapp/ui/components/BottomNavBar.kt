package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.navigation.BottomNavbarItem
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.navigation.Screen

@Composable
fun BottomNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomNavbarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            BottomNavbarItem(
                title = stringResource(R.string.menu_activity),
                icon =  ImageVector.vectorResource(R.drawable.ic_activity),
                screen = Screen.Activity
            ),
            BottomNavbarItem(
                title = stringResource(R.string.menu_search),
                icon = Icons.Default.Search,
                screen = Screen.Activity
            ),
            BottomNavbarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            ),
        )
        navigationItems.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = it.title == navigationItems[0].title,
                selectedContentColor = Color(0xFFFFA91A),
                unselectedContentColor = LightGray,
                onClick = {}
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        BottomNavBar(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
