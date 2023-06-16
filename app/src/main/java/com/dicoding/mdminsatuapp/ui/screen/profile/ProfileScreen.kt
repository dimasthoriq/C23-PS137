package com.dicoding.mdminsatuapp.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.data.local.SessionManager
import com.dicoding.mdminsatuapp.dummy.arts
import com.dicoding.mdminsatuapp.dummy.edu
import com.dicoding.mdminsatuapp.dummy.sports
import com.dicoding.mdminsatuapp.dummy.travel
import com.dicoding.mdminsatuapp.ui.components.*
import com.dicoding.mdminsatuapp.ui.screen.quicksurvey.QuickSurveyViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,
    sessionManager: SessionManager
) {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val selectedChips = remember { mutableStateListOf<SurveyChipData>() }
    val isSelectedState = remember { mutableStateOf(false) }
    val showLogoutDialog = remember { mutableStateOf(false) }

    val selectedSportsCount = remember { mutableStateOf(0) }
    val selectedArtsCount = remember { mutableStateOf(0) }
    val selectedTravelCount = remember { mutableStateOf(0) }
    val selectedEduCount = remember { mutableStateOf(0) }

    val viewmodel = QuickSurveyViewModel()



    Scaffold(
        topBar = {
            TopBar(text = "Profile")
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                CustomTabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.padding(vertical = 8.dp),
                    tabs = listOf("Profile", "Interest"),
                    onTabSelected = { index -> selectedTabIndex.value = index }
                )
                if (selectedTabIndex.value == 1) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        state = rememberLazyListState()
                    ) {
                        item {
                            Text(text = "Edit Your Interest", style = MaterialTheme.typography.h4, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Choose 3 or more activities you might be interested in!")
                        }

                        item { SurveyChipsGroup("Sports", sports,selectedChips,viewmodel) }
                        item { SurveyChipsGroup("Arts", arts,selectedChips,viewmodel) }
                        item { SurveyChipsGroup("Travel", travel,selectedChips, viewmodel) }
                        item { SurveyChipsGroup("Edu", edu,selectedChips, viewmodel) }

                        item {
                            Spacer(modifier = Modifier.height(32.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                PrimaryButton(
                                    text = "Save",
                                    onClick = {

                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp)
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
                                GenderChip(
                                    text = "Male",
                                    isSelected = isSelectedState.value,
                                    onChipClicked = { isSelectedState.value = true }
                                )
                                GenderChip(
                                    text = "Female",
                                    isSelected = !isSelectedState.value,
                                    onChipClicked = { isSelectedState.value = false }
                                )
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
                                onClick = {
                                    showLogoutDialog.value = true

                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                }
                Box {
                    BottomNavBar(
                        navController = navController,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }


            }
        }
    )

    CustomSuccessDialog(
        showDialog = showLogoutDialog.value,
        onDismiss = {
            showLogoutDialog.value = false
            sessionManager.clearSession()
            navController.navigate("splash_screen")
        },
        title = "Success",
        message = "Logout Success"
    )

}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = NavController(LocalContext.current),
        sessionManager = SessionManager(context = LocalContext.current)
    )
}


