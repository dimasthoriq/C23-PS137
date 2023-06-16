package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.ui.components.CounterTextField
import com.dicoding.mdminsatuapp.ui.components.CustomOutlinedButton
import com.dicoding.mdminsatuapp.ui.components.DistanceSelection
import com.dicoding.mdminsatuapp.ui.components.GenderSelection
import kotlinx.coroutines.launch

@Composable
fun QuickSurveyBioScreen(
    navController: NavController,
    quickSurveyViewModel: QuickSurveyViewModel
) {
    var age by remember { mutableStateOf(0) }
    var gender by remember { mutableStateOf(true) }
    var distance by remember { mutableStateOf("") }
    var isNextButtonEnabled by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    fun collectInputs() {
        isNextButtonEnabled = age > 0 && distance.isNotEmpty()
    }

    fun showErrorMessage(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Quick Survey",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "How old are you?")
        CounterTextField(age) { newAge ->
            age = newAge
            collectInputs()
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "How would you classify your gender?")
        GenderSelection(gender) { newGender ->
            gender = newGender
            collectInputs()
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "How far is the distance you're willing to travel for your hobbies?")
        DistanceSelection(distance) { newDistance ->
            distance = newDistance
            collectInputs()
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            CustomOutlinedButton(
                text = "Next",
                onClick = {
                    if (isNextButtonEnabled) {
                        quickSurveyViewModel.age = age
                        quickSurveyViewModel.gender = gender
                        quickSurveyViewModel.distance = distance

                        navController.navigate("interest")
                    } else {
                        showErrorMessage("Please fill in all the fields")
                    }
                },
                enabled = isNextButtonEnabled
            )
        }
    }

    SnackbarHost(
        hostState = scaffoldState.snackbarHostState,
        modifier = Modifier.padding(16.dp)
    ) { snackbarData ->
        Snackbar(
            modifier = Modifier.padding(8.dp),
            backgroundColor = Color.White,
            contentColor = Color.Black,
            action = {
                Button(
                    onClick = {
                        scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                    }
                ) {
                    Text(text = "Dismiss")
                }
            }
        ) {
            Text(text = snackbarData.message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickSurveyPreview() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            QuickSurveyBioScreen(
                navController = NavController(LocalContext.current),
                quickSurveyViewModel = QuickSurveyViewModel()
            )
        }
    }
}
