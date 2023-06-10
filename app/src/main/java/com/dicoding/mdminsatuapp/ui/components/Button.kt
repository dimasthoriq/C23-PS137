package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.theme.Shapes

@Composable
fun CustomOutlinedButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        contentPadding = PaddingValues(42.dp, 4.dp),
        border = BorderStroke(2.dp, colorResource(id = R.color.yellow)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = colorResource(id = R.color.yellowDark)
        )
    )
    {
        Text(text = text)

    }

}

@Composable
@Preview
fun OutlinedButtonPreview() {
    CustomOutlinedButton(text = "Next", onClick = {})
}

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        contentPadding = PaddingValues(64.dp, 12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.yellow),
            contentColor = colorResource(id = R.color.black)
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
@Preview
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "Ask to Join",
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}



@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isSelected) {
        Button(
            onClick = onClick,
            modifier = modifier
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = text)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Black
            )
        ) {
            Text(text = text)
        }
    }
}



@Composable
fun CustomTabRow(
    selectedTabIndex: MutableState<Int>,
    modifier: Modifier = Modifier,
    tabs: List<String>,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        tabs.forEachIndexed { index, tabText ->
            TabButton(
                text = tabText,
                isSelected = selectedTabIndex.value == index,
                onClick = { onTabSelected(index) },
                modifier = modifier.weight(1f)
            )
        }
    }
}