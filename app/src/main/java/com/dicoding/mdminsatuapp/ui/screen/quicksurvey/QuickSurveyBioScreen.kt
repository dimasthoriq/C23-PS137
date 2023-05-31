package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.ui.components.CounterButton
import com.dicoding.mdminsatuapp.ui.components.CustomOutlinedButton
import com.dicoding.mdminsatuapp.ui.components.DistanceSelection
import com.dicoding.mdminsatuapp.ui.components.GenderSelection


@Composable
fun QuickSurveyBioScreen(
    navController: NavController
) {
    var age by remember { mutableStateOf(0) }
    var gender by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Quick Survey", style = MaterialTheme.typography.h4, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "How old are you?")
        CounterButton(age) { newAge -> age = newAge }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "How would you classify your gender?")
        GenderSelection(gender) { newGender -> gender = newGender }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "How far is the distance you're willing to travel for your hobbies?")
        DistanceSelection(distance) { newDistance -> distance = newDistance }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            CustomOutlinedButton(
                text = "Next",
                onClick = {
                  navController.navigate("interest")
                }
            )
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
            QuickSurveyBioScreen(navController = NavController(LocalContext.current))
        }

    }
}