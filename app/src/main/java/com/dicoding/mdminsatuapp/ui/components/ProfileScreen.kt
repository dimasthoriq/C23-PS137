package com.dicoding.mdminsatuapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.dummy.arts
import com.dicoding.mdminsatuapp.dummy.edu
import com.dicoding.mdminsatuapp.dummy.sports
import com.dicoding.mdminsatuapp.dummy.travel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopBar(text = "Profile")
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CustomTabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.padding(vertical = 8.dp),
                tabs = listOf("Profile", "Interest"),
                onTabSelected = { index -> selectedTabIndex.value = index }
            )
            if (selectedTabIndex.value == 1) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    state = rememberLazyListState()
                ) {
                    item {
                        Text(
                            text = "Edit Your Interest",
                            style = MaterialTheme.typography.h4,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                        Text(
                            text = "Choose 3 or more activities you might be interested in!",
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        SurveyChipsGroup("Sports", sports)
                        SurveyChipsGroup("Arts", arts)
                        SurveyChipsGroup("Travel", travel)
                        SurveyChipsGroup("Edu", edu)
                        Spacer(modifier = Modifier.height(32.dp))
                        PrimaryButton(
                            text = "Save",
                            onClick = { },
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    state = rememberLazyListState()
                ) {
                    item {
                        Text(
                            text = "Account Detail",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                        LabelTextField(
                            label = "Full Name",
                            placeholder = "Enter your full name",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LabelTextField(
                            label = "Email",
                            placeholder = "Enter your email",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LabelTextField(
                            label = "Age",
                            placeholder = "Enter your age",
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 16.dp)
                        ) {
                            GenderChip(text = "Male", isSelected = true)
                            GenderChip(text = "Female", isSelected = false)
                        }
                        LabelTextField(
                            label = "Address",
                            placeholder = "Enter your address",
                            isTextArea = true,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        PrimaryButton(
                            text = "Logout",
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = NavController(LocalContext.current))
}
