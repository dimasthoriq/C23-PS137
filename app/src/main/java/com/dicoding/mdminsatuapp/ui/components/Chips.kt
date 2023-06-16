package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.screen.quicksurvey.QuickSurveyViewModel

data class ChipData(
    val icon: Int,
    val title: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
)

data class SurveyChipData(
    val icon: Int,
    val selectedIcon: Int,
    val title: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
)

@Composable
fun SurveyChipsGroup(
    title: String,
    chips: List<SurveyChipData>,
    selectedChips: MutableList<SurveyChipData>,
    viewModel: QuickSurveyViewModel
) {
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
                    selectedChips = selectedChips,
                    viewModel = viewModel
                )
            }
        }
    }
}


@Composable
fun SurveyChips(
    iconResId: Int,
    text: String,
    chip: SurveyChipData,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    selectedChips: MutableList<SurveyChipData>,
    viewModel: QuickSurveyViewModel
) {
    val selected by remember { chip.selected }
    val borderColor = if (selected) Color(0xFFFFDE59) else Color.Gray

    Column(
        modifier = modifier
            .clickable { viewModel.handleChipSelection(chip, selectedChips) }
            .border(1.dp, borderColor, shape = shape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = if(selected) chip.selectedIcon else chip.icon),
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
        SurveyChipData(R.drawable.ic_futsal, R.drawable.ic_futsal_selected, "Futsal"),
        SurveyChipData(R.drawable.ic_basketball, R.drawable.ic_basketball_selected, "Badminton"),
        SurveyChipData(R.drawable.ic_badminton, R.drawable.ic_badminton_selected, "Basketball"),
    )
    val selectedSports = remember { mutableStateListOf<SurveyChipData>() }

    val arts = listOf(
        SurveyChipData(R.drawable.ic_pottery, R.drawable.ic_pottery_selected,  "Pottery"),
        SurveyChipData(R.drawable.ic_painting,R.drawable.ic_painting_selected, "Painting"),
        SurveyChipData(R.drawable.ic_music,R.drawable.ic_music_selected, "Music"),
    )
    val selectedArts = remember { mutableStateListOf<SurveyChipData>() }

    val travel = listOf(
        SurveyChipData(R.drawable.ic_hiking,R.drawable.ic_hiking_selected ,"Hiking"),
        SurveyChipData(R.drawable.ic_diving,R.drawable.ic_diving_selected, "Diving"),
        SurveyChipData(R.drawable.ic_rafting,R.drawable.ic_rafting_selected, "Rafting"),
    )
    val selectedTravel = remember { mutableStateListOf<SurveyChipData>() }

    val edu = listOf(
        SurveyChipData(R.drawable.ic_workshop, R.drawable.ic_workshop_selected, "Workshop"),
        SurveyChipData(R.drawable.ic_teaching, R.drawable.ic_teaching_selected, "Teaching"),
        SurveyChipData(R.drawable.ic_study, R.drawable.ic_study_selected, "Study Club"),
    )
    val selectedEdu = remember { mutableStateListOf<SurveyChipData>() }

    val viewModel = viewModel(
        modelClass = QuickSurveyViewModel::class.java,
        factory = ViewModelProvider.NewInstanceFactory()
    )
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            SurveyChipsGroup("Sports", sports, selectedSports, viewModel)
            SurveyChipsGroup("Arts", arts, selectedArts, viewModel)
            SurveyChipsGroup("Travel", travel, selectedTravel, viewModel)
            SurveyChipsGroup("Edu", edu, selectedEdu, viewModel)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
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
                border = if (isSelected) BorderStroke(
                    1.dp,
                    MaterialTheme.colors.primary
                ) else BorderStroke(1.dp, MaterialTheme.colors.onSurface),
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
fun GenderChip(text: String, isSelected: Boolean, onChipClicked: () -> Unit) {
    val chipColor = Color.Transparent
    val borderColor = if (isSelected) colorResource(id = R.color.yellowDark) else Color.LightGray

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
            .clickable { onChipClicked() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val iconResId = if (text == "Male") R.drawable.ic_male else R.drawable.ic_female
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 8.dp, end = 32.dp, top = 8.dp, bottom = 8.dp),
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CategoryChipsGroup(
    chips: List<ChipData>,
    navController: NavController
) {
    Column(modifier = Modifier.padding(start = 0.dp, end = 0.dp, top = 4.dp, bottom = 4.dp)) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(chips.size) { index ->
                val chip = chips[index]
                CategoryChip(
                    iconResId = chip.icon,
                    text = chip.title,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun CategoryChip(
    iconResId: Int,
    text: String,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = modifier
            .clickable { navController.navigate("category/${text}") }
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                modifier = Modifier
                    .wrapContentWidth()
            )
        }
    }
}

