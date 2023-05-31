package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mdminsatuapp.R

data class ChipData(
    val icon: Int,
    val title: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
)

@Composable
fun SurveyChipsGroup(title: String, chips: List<ChipData>) {
    Column(modifier = Modifier.padding(start = 0.dp, end = 0.dp, top = 4.dp, bottom = 4.dp)) {
        Text(text = title, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.width(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(chips.size) { index ->
                val chip = chips[index]
                SurveyChips(
                    iconResId = chip.icon,
                    text = chip.title,
                    chip = chip,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun SurveyChips(
    iconResId: Int,
    text: String,
    chip: ChipData,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp)
) {
    var selected by chip.selected
    val borderColor = if (selected) Color(0xFFFFDE59) else Color.Gray

    Column(
        modifier = modifier
            .clickable { selected = !selected }
            .border(1.dp, borderColor, shape = shape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun SurveyChipsPreview() {
    val sports = listOf(
        ChipData(R.drawable.ic_futsal, "Futsal"),
        ChipData(R.drawable.ic_basketball, "Badminton"),
        ChipData(R.drawable.ic_badminton, "Basketball"),
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

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            SurveyChipsGroup("Sports", sports)
            SurveyChipsGroup("Arts", arts)
            SurveyChipsGroup("Travel", travel)
            SurveyChipsGroup("Edu", edu)

        }

    }
}