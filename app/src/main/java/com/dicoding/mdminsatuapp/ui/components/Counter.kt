package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterButton(age: Int, onAgeChange: (Int) -> Unit) {
    val buttonColor = Color(0xFFFFDE59)
    val textColor = Color(0xFF000000)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { if (age > 0) onAgeChange(age - 1) },
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                "-",
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontSize = 16.sp
            )
        }

        Text(
            text = age.toString(), modifier = Modifier.padding(horizontal = 28.dp))

        Button(
            onClick = { onAgeChange(age + 1) },
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            modifier = Modifier.padding(4.dp)

        ) {
            Text(
                "+",
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontSize = 16.sp
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterButtonPreview() {
    CounterButton(0, onAgeChange = {})
}
