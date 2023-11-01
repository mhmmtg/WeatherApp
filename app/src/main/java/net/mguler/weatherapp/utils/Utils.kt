package net.mguler.weatherapp.utils

import net.mguler.weatherapp.R

object Utils {
    const val PREFS = "net.mguler.myweather2"

    val weatherMap = mapOf(
        -1 to "Loading..",
        0 to "Clear sky",
        1 to "Mainly clear",
        2 to "Partly cloudy",
        3 to "Overcast",
        45 to "Fog and depositing rime fog",
        48 to "Fog and depositing rime fog",
        51 to "Drizzle: Light intensity",
        53 to "Drizzle: Moderate intensity",
        55 to "Drizzle: Dense intensity",
        56 to "Freezing Drizzle: Light intensity",
        57 to "Freezing Drizzle: Dense intensity",
        61 to "Rain: Slight intensity",
        63 to "Rain: Moderate intensity",
        65 to "Rain: Heavy intensity",
        66 to "Freezing Rain: Light intensity",
        67 to "Freezing Rain: Heavy intensity",
        71 to "Snow fall: Slight intensity",
        73 to "Snow fall: Moderate intensity",
        75 to "Snow fall: Heavy intensity",
        77 to "Snow grains",
        80 to "Rain showers: Slight intensity",
        81 to "Rain showers: Moderate intensity",
        82 to "Rain showers: Violent intensity",
        85 to "Snow showers: Slight intensity",
        86 to "Snow showers: Heavy intensity",
        95 to "Thunderstorm: Slight or moderate",
        96 to "Thunderstorm with slight hail",
        99 to "Thunderstorm with heavy hail"
    )

    val weatherImg = mapOf(
        -1 to R.drawable.ic_downloading,
        0 to R.drawable.day,
        1 to R.drawable.cloudy_day_1,
        2 to R.drawable.cloudy_day_3,
        3 to R.drawable.cloudy,
        45 to R.drawable.img03d,
        48 to R.drawable.img03d,
        51 to R.drawable.rainy_1,
        53 to R.drawable.rainy_1,
        55 to R.drawable.rainy_1,
        56 to R.drawable.rainy_2,
        57 to R.drawable.rainy_2,
        61 to R.drawable.rainy_3,
        63 to R.drawable.rainy_3,
        65 to R.drawable.rainy_3,
        66 to R.drawable.rainy_4,
        67 to R.drawable.rainy_4,
        71 to R.drawable.snowy_1,
        73 to R.drawable.snowy_2,
        75 to R.drawable.snowy_3,
        77 to R.drawable.snowy_4,
        80 to R.drawable.rainy_5,
        81 to R.drawable.rainy_6,
        82 to R.drawable.rainy_7,
        85 to R.drawable.snowy_5,
        86 to R.drawable.snowy_6,
        95 to R.drawable.thunder,
        96 to R.drawable.thunder,
        99 to R.drawable.thunder
    )

}