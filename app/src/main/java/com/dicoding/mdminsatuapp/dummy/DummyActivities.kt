import com.dicoding.mdminsatuapp.R
import com.dicoding.mdminsatuapp.ui.components.ActivityCardData

fun getDummyActivityList(): List<List<String>> {
    return listOf(
        listOf(
            "Zumba Session",
            "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_23.jpg",
            "09 July 2023",
            "Urban Gym, Jl. Dago Asri No.8"
        ),
        listOf(
            "Kelas Batik Celup Jogja",
            "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_26.jpg",
            "16 July 2023",
            "Jl. Pelem Sari I 160, Prenggan, Kota Yogyakarta"
        ),
        listOf(
            "Jatim Park Family Day",
            "https://raw.githubusercontent.com/okkyPratama/minsatuDummyImg/main/header_7.jpg",
            "20 July 2023",
            "Jawa Timur Park 2, Jl. Jatim Park II"
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
