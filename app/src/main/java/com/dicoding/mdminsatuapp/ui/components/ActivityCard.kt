package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.mdminsatuapp.R

@Composable
fun ActivityCard(
    title: String,
    photoUrl: String,
    date: String,
    location: String,
    dateIcon: Int,
    locationIcon: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .size(80.dp)
                .padding(6.dp)
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = dateIcon),
                    contentDescription = null,
                )
                Text(
                    text = date,
                    Modifier.padding(start = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = locationIcon),
                    contentDescription = null,
                )
                Text(
                    text = location,
                    Modifier.padding(start = 8.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ActivityCardPreview() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clickable { },
            elevation = 5.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            ActivityCard(
                title = "Tes Aktivitas",
                photoUrl = "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
                date = "Date",
                location = "Location",
                dateIcon = R.drawable.ic_calendar,
                locationIcon = R.drawable.ic_location,
            )

        }

}
