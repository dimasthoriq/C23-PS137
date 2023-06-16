package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonOption(text: String, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        modifier = Modifier
            .selectable(selected = isSelected, onClick = onSelect),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(text = text)
    }
}

@Composable
fun GenderSelection(isMale: Boolean, isFemale: Boolean, onMaleSelected: () -> Unit, onFemaleSelected: () -> Unit) {
    Row {
        RadioButtonOption(
            text = "Male",
            isSelected = isMale,
            onSelect = onMaleSelected
        )

        RadioButtonOption(
            text = "Female",
            isSelected = isFemale,
            onSelect = onFemaleSelected
        )
    }
}


@Composable
fun DistanceSelection(selectedDistance: String, onDistanceSelected: (String) -> Unit) {
    Row {
        RadioButtonOption(
            text = "<10 km",
            isSelected = selectedDistance == "<10 km",
            onSelect = { onDistanceSelected("<10 km") }
        )

        RadioButtonOption(
            text = "10-50 km",
            isSelected = selectedDistance == "10-50 km",
            onSelect = { onDistanceSelected("10-50 km") }
        )

        RadioButtonOption(
            text = ">50 km",
            isSelected = selectedDistance == ">50 km",
            onSelect = { onDistanceSelected(">50 km") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonPreview() {
//    GenderSelection(selectedGender = true , onGenderSelected = {} )
}