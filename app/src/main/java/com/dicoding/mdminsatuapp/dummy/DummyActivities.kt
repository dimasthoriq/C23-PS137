import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.components.ActivityCardData

fun getDummyActivityList(): List<List<String>> {
    return listOf(
        listOf(
            "Activity 1",
            "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
            "Date 1",
            "Location 1"
        ),
        listOf(
            "Activity 2",
            "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
            "Date 2",
            "Location 2"
        ),
        listOf(
            "Activity 3",
            "https://raw.githubusercontent.com/okkyPratama/composedummyimages/main/data-dummy-compose-app/product_1.jpg",
            "Date 3",
            "Location 3"
        )
    )
}

 fun getDummyUpcomingActivities(): List<ActivityCardData> {
     return listOf(
         ActivityCardData(
             title = "Night Busking @GBK",
             photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_2.jpeg",
             date = "07 June 2023",
             location = "Gelora Bung Karno",
             dateIcon = R.drawable.ic_calendar,
             locationIcon = R.drawable.ic_location
         )
     )
 }

fun getDummyDoneActivities(): List<ActivityCardData> {
    return listOf(
        ActivityCardData(
            title = "Belajar Bareng UTBK Jakpus",
            photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_27.jpg",
            date = "10 Januari 2023",
            location = "Perpustakaan Nasional Republik Indonesia",
            dateIcon = R.drawable.ic_calendar,
            locationIcon = R.drawable.ic_location
        ),
        ActivityCardData(
            title = "Ballet Class Namarina",
            photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_28.jpg",
            date = "21 Februari 2023",
            location = "Jalan Halimun Raya No.43",
            dateIcon = R.drawable.ic_calendar,
            locationIcon = R.drawable.ic_location
        ),
        ActivityCardData(
            title = "Hiking Rinjani",
            photoUrl = "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_29.jpg",
            date = "11 Maret 2023",
            location = " Sembalun, East Lombok Regency, West Nusa Tenggara",
            dateIcon = R.drawable.ic_calendar,
            locationIcon = R.drawable.ic_location
        ),
    )
}
