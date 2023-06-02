package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    text: String,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit = {}
) {
    TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
        if (navigationIcon != null) {
            IconButton(onClick = onNavigationIconClick) {
                Icon(navigationIcon, contentDescription = null)
            }
        }
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(
        text = "Activity",
        onNavigationIconClick = {

        }
    )

}

@Preview(showBackground = true)
@Composable
fun TopBarPreviewWithNavigationIcon() {
    TopBar(
        text = "Activity",
        navigationIcon = Icons.Default.ArrowBack,
        onNavigationIconClick = {

        }
    )

}

