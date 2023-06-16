package com.dicoding.mdminsatuapp.ui.components

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

data class BucketCardData(
    val title: String,
    val photoUrl: String,
    val date: String,
    val location: String,
    val dateIcon: Int,
    val locationIcon: Int,
    val bucketIcon: Int
)

@Composable
fun BucketCard(
    title: String,
    photoUrl: String,
    date: String,
    location: String,
    dateIcon: Int,
    locationIcon: Int,
    bucketIcon: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.size(80.dp)
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Icon(
                        painter = painterResource(id = bucketIcon),
                        contentDescription = null,
                        Modifier.padding(end = 4.dp)
                    )
                }

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
}


@Preview(showBackground = true)
@Composable
fun BucketCardPreview() {
    BucketCard(
        title = "Fun Game Voli Velo",
        photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_16.jpg",
        date = "16 July 2023",
        location = "Jakarta International Velodrome, Jl. Balap Sepeda No.35",
        dateIcon = R.drawable.ic_calendar,
        locationIcon = R.drawable.ic_location,
        bucketIcon = R.drawable.ic_bucket_fill
    )
}
