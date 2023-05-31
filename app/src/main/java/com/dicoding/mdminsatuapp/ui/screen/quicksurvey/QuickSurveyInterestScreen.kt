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
import com.dicoding.mdminsatuapp.ui.components.ChipData
import com.dicoding.mdminsatuapp.ui.components.PrimaryButton
import com.dicoding.mdminsatuapp.ui.components.SurveyChipsGroup

@Composable
fun QuickSurveyInterestScreen(
    navController: NavController
) {
    val sports = listOf(
        ChipData(R.drawable.ic_futsal, "Futsal"),
        ChipData(R.drawable.ic_basketball, "Basketball"),
        ChipData(R.drawable.ic_badminton, "Badminton"),
    )

    val arts = listOf(
        ChipData(R.drawable.ic_pottery, "Pottery"),
        ChipData(R.drawable.ic_painting, "Painting"),
        ChipData(R.drawable.ic_music, "Music"),
    )

    val travel = listOf(
        ChipData(R.drawable.ic_hiking, "Hiking"),
        ChipData(R.drawable.ic_diving, "Diving"),
        ChipData(R.drawable.ic_rafting, "Rafting"),
    )

    val edu = listOf(
        ChipData(R.drawable.ic_workshop, "Workshop"),
        ChipData(R.drawable.ic_teaching, "Teaching"),
        ChipData(R.drawable.ic_study, "Study Club"),
    )

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
                    onClick = {

                    },
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