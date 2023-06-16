package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.data.local.SessionManager
import com.dicoding.mdminsatuapp.dummy.arts
import com.dicoding.mdminsatuapp.dummy.edu
import com.dicoding.mdminsatuapp.dummy.sports
import com.dicoding.mdminsatuapp.dummy.travel
import com.dicoding.mdminsatuapp.navigation.Screen
import com.dicoding.mdminsatuapp.ui.components.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun QuickSurveyInterestScreen(
    navController: NavController,
    quickSurveyViewModel: QuickSurveyViewModel
) {
    val context = LocalContext.current
    val selectedChips = remember { mutableStateListOf<SurveyChipData>() }
    val sessionManager = SessionManager(context)

    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorDialogMessage by remember { mutableStateOf("") }
    var successDialogMessage by remember { mutableStateOf("") }


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            state = rememberLazyListState()
        ) {

            item {
                Text(
                    text = "Quick Survey",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Choose 3 or more activities you might be interested in!")
            }

            item {
                SurveyChipsGroup(
                    title = "Sports",
                    chips = sports,
                    selectedChips = selectedChips,
                    viewModel = quickSurveyViewModel
                )
            }
            item {
                SurveyChipsGroup(
                    title = "Arts",
                    chips = arts,
                    selectedChips = selectedChips,
                    viewModel = quickSurveyViewModel
                )
            }
            item {
                SurveyChipsGroup(
                    title = "Travel",
                    chips = travel,
                    selectedChips = selectedChips,
                    viewModel = quickSurveyViewModel
                )
            }
            item {
                SurveyChipsGroup(
                    title = "Edu",
                    chips = edu,
                    selectedChips = selectedChips,
                    viewModel = quickSurveyViewModel
                )
            }


            item {
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = selectedChips.size >= 3,
                        text = "Done",
                        onClick = {
                            if (selectedChips.size >= 3) {
                                val userId = sessionManager.getUserId()
                                if (userId != null) {

                                    quickSurveyViewModel.uploadQuickSurveyData(
                                        userId = userId,
                                        onSuccess = {
                                            showSuccessDialog = true
                                            successDialogMessage = "Survey submitted successfully."
                                        },
                                        onError = { errorMessage ->
                                            showErrorDialog = true
                                            errorDialogMessage = errorMessage
                                        }
                                    )
                                }

                            } else {
                                Toast.makeText(
                                    context,
                                    R.string.error_select_chips,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    )
                }
            }

        }
    }

    CustomSuccessDialog(
        showDialog = showSuccessDialog,
        onDismiss = { showSuccessDialog = false },
        message = successDialogMessage
    )

    CustomErrorDialog(
        showDialog = showErrorDialog,
        onDismiss = { showErrorDialog = false },
        message = errorDialogMessage
    )

    LaunchedEffect(showSuccessDialog) {
        if (showSuccessDialog) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.QuickSurveyInterest.route) { inclusive = true }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun QuickSurveyInterestPreview() {
    val navController = rememberNavController()

    QuickSurveyInterestScreen(
        navController = navController,
        quickSurveyViewModel = QuickSurveyViewModel()
    )
}
