package com.dicoding.mdminsatuapp.ui.screen.quicksurvey

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.dummy.arts
import com.dicoding.mdminsatuapp.dummy.edu
import com.dicoding.mdminsatuapp.dummy.sports
import com.dicoding.mdminsatuapp.dummy.travel
import com.dicoding.mdminsatuapp.navigation.Screen
import com.dicoding.mdminsatuapp.ui.components.ChipData
import com.dicoding.mdminsatuapp.ui.components.PrimaryButton
import com.dicoding.mdminsatuapp.ui.components.SurveyChipsGroup

@Composable
fun QuickSurveyInterestScreen(
    navController: NavController

) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        state = rememberLazyListState()
    ) {
        item {
            Text(text = "Quick Survey", style = MaterialTheme.typography.h4, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Choose 3 or more activities you might be interested in!")
        }

        item { SurveyChipsGroup("Sports", sports) }
        item { SurveyChipsGroup("Arts", arts) }
        item { SurveyChipsGroup("Travel", travel) }
        item { SurveyChipsGroup("Edu", edu) }

        item {
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                PrimaryButton(
                    text = "Done",
                    onClick = { navController.navigate(Screen.Home.route) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickSurveyInterestPreview() {
    QuickSurveyInterestScreen(navController = NavController(LocalContext.current))

}