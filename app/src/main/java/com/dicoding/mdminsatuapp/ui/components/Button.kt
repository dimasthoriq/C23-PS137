package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
fun OutlinedButton(
    text: String,
    onClick: () -> Unit,
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
    OutlinedButton(text = "Next", onClick = {})
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
fun GoogleButton(
    modifier: Modifier = Modifier,
    text: String = "Sign In with Google",
    loadingText: String = "Creating Account...",
    icon: Int = R.drawable.ic_google_logo,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClicked: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier.clickable { clicked = !clicked },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google Button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            ) {
                Text(
                    text = if (clicked) loadingText else text,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
                onClicked()
            }
        }
    }
}


@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton(
        text = "Sign In with Google",
        loadingText = "Creating Account...",
        onClicked = {}
    )
}