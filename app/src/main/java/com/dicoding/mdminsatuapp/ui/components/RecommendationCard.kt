package com.dicoding.mdminsatuapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.mdminsatuapp.R

data class RecommendationCardData(
    val title: String,
    val photoUrl: String,
    val date: String,
    val time: String,
    val location: String,
    val dateIcon: Int,
    val timeIcon: Int,
    val locationIcon: Int,
)

@Composable
fun RecommendationCard(
    title: String,
    photoUrl: String,
    date: String,
    time: String,
    location: String,
    dateIcon: Int,
    timeIcon: Int,
    locationIcon: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding( vertical = 4.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .shadow(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier,
                    style = MaterialTheme.typography.h6, // Increase the font size
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = timeIcon),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = time,
                        Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = dateIcon),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = date,
                        Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = locationIcon),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = location,
                        Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.caption
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))

            }
        }
    }
}


@Composable
fun RecommendationCardsList(
    cards: List<RecommendationCardData>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)

    ) {
        items(cards) { card ->
            RecommendationCard(
                title = card.title,
                photoUrl = card.photoUrl,
                date = card.date,
                time = card.time,
                location = card.location,
                dateIcon = card.dateIcon,
                timeIcon = card.timeIcon,
                locationIcon = card.locationIcon,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecommendationCard() {

    val cards = listOf(
        RecommendationCardData(
            title = "Mixue Tanjung Duren",
            photoUrl = "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
            time = "19.00 - 21.00",
            date = "12 Agustus 2021",
            location = "Jl. Jelambar Barat No. 38, Jakarta Barat",
            dateIcon = R.drawable.ic_calendar,
            timeIcon = R.drawable.ic_time,
            locationIcon = R.drawable.ic_location
        ),

        RecommendationCardData(
            title = "Mixue Tanjung Duren",
            photoUrl = "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
            time = "19.00 - 21.00",
            date = "12 Agustus 2021",
            location = "Jl. Jelambar Barat No. 38, Jakarta Barat",
            dateIcon = R.drawable.ic_calendar,
            timeIcon = R.drawable.ic_time,
            locationIcon = R.drawable.ic_location
        ),

        RecommendationCardData(
            title = "Mixue Tanjung Duren",
            photoUrl = "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
            time = "19.00 - 21.00",
            date = "12 Agustus 2021",
            location = "Jl. Jelambar Barat No. 38, Jakarta Barat",
            dateIcon = R.drawable.ic_calendar,
            timeIcon = R.drawable.ic_time,
            locationIcon = R.drawable.ic_location
        ),
    )

    RecommendationCardsList(cards)

//    RecommendationCard(
//        title = "Mixue Tanjung Duren",
//        photoUrl = "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
//        time = "19.00 - 21.00",
//        date = "12 Agustus 2021",
//        location = "Jl. Jelambar Barat No. 38, Jakarta Barat",
//        dateIcon = R.drawable.ic_calendar,
//        timeIcon = R.drawable.ic_time,
//        locationIcon = R.drawable.ic_location
//    )



}