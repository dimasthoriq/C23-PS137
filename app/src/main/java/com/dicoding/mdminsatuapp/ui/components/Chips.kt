package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mdminsatuapp.R

data class ChipData(
    val icon: Int,
    val title: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
)

@Composable
fun SurveyChipsGroup(title: String, chips: List<ChipData>, selectedChips: MutableList<ChipData>) {
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
                    modifier = Modifier.padding(8.dp),
                    selectedChips = selectedChips
                )
            }
        }
    }
}


@Composable
fun CategoryChips(chips: List<ChipData>) {
    Column(modifier = Modifier.padding(start = 0.dp, end = 0.dp, top = 4.dp, bottom = 4.dp)) {
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
                    modifier = Modifier.padding(8.dp),

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
    shape: Shape = RoundedCornerShape(8.dp),
    selectedChips: MutableList<ChipData> = mutableListOf()
) {
    val selected by remember { chip.selected }
    val borderColor = if (selected) Color(0xFFFFDE59) else Color.Gray

    Column(
        modifier = modifier
            .clickable { handleChipSelection(chip, selectedChips) }
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


fun handleChipSelection(chip: ChipData, selectedChips: MutableList<ChipData>) {
    chip.selected.value = !chip.selected.value
    if (chip.selected.value) {
        selectedChips.add(chip)
    } else {
        selectedChips.remove(chip)
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
    val selectedSports = remember { mutableStateListOf<ChipData>() }

    val arts = listOf(
        ChipData(R.drawable.ic_pottery, "Pottery"),
        ChipData(R.drawable.ic_painting, "Painting"),
        ChipData(R.drawable.ic_music, "Music"),
    )
    val selectedArts = remember { mutableStateListOf<ChipData>() }

    val travel = listOf(
        ChipData(R.drawable.ic_hiking, "Hiking"),
        ChipData(R.drawable.ic_diving, "Diving"),
        ChipData(R.drawable.ic_rafting, "Rafting"),
    )
    val selectedTravel = remember { mutableStateListOf<ChipData>() }

    val edu = listOf(
        ChipData(R.drawable.ic_workshop, "Workshop"),
        ChipData(R.drawable.ic_teaching, "Teaching"),
        ChipData(R.drawable.ic_study, "Study Club"),
    )
    val selectedEdu = remember { mutableStateListOf<ChipData>() }

    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            SurveyChipsGroup("Sports", sports, selectedSports)
            SurveyChipsGroup("Arts", arts, selectedArts)
            SurveyChipsGroup("Travel", travel, selectedTravel)
            SurveyChipsGroup("Edu", edu, selectedEdu)

        }
    }
}


@Composable
fun FilterChipsGroup() {
    val chips = listOf("Category", "Activity", "Community")

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Filter",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            chips.forEach { chip ->
                FilterChip(text = chip)
            }
        }
    }
}

@Composable
fun FilterChip(text: String) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clickable { isSelected = !isSelected }
            .background(Color.Transparent)
            .padding(8.dp)
            .border(
                border = if (isSelected) BorderStroke(1.dp, MaterialTheme.colors.primary) else BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                shape = MaterialTheme.shapes.small
            )

    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.body2,
            color = if (isSelected) Color.Black else MaterialTheme.colors.onSurface
        )
    }
}




@Preview(showBackground = true)
@Composable
fun FilterChipsPreview() {
    FilterChipsGroup()
}


@Composable
fun GenderChip(text: String, isSelected: Boolean) {
    val chipColor =  Color.Transparent
    val borderColor = if (isSelected) Color(0xFFFFA91A) else Color.Gray
    val textColor =  Color.Black

    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(IntrinsicSize.Max)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .background(chipColor)
            .clickable { }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.body2,
            color = textColor
        )
    }
}

