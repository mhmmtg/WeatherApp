package net.mguler.weatherapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import net.mguler.weatherapp.R
import net.mguler.weatherapp.model.NextHours
import net.mguler.weatherapp.model.NextHoursAdapter
import net.mguler.weatherapp.network.Hourly
import net.mguler.weatherapp.utils.WeatherViewModel.*
import java.time.LocalDateTime

@BindingAdapter("currentDegree")
fun bindCurrentDegree(textView: TextView, currentDegree: Double?) {
    currentDegree?.let {
        textView.text = String.format("%.0f", it)
    }
}

@BindingAdapter("currentImage")
fun bindCurrentImage(imageView: ImageView, currentImage: Int?) {
    currentImage?.let {
        imageView.load(Utils.weatherImg[it])
    }
}

@BindingAdapter("currentWeather")
fun bindCurrentWeather(textView: TextView, currentWeather: Int?) {
    currentWeather?.let {
        textView.text = Utils.weatherMap[it]
    }
}

@BindingAdapter("currentWind")
fun bindCurrentWind(textView: TextView, currentWind: Double?) {
    currentWind?.let {
        val text = "${it}km/h"
        textView.text = text
    }
}

@BindingAdapter("imgStatus")
fun bindImgStatus(bar: ProgressBar, status: WeatherStatus) {
    when (status) {
        WeatherStatus.LOADING -> {
            bar.visibility = View.VISIBLE
        }

        WeatherStatus.DONE -> {
            bar.visibility = View.GONE
        }

        WeatherStatus.ERROR -> {
            bar.visibility = View.GONE
        }
    }
}


@BindingAdapter("hourlyData")
fun bindRecyclerView(recyclerView: RecyclerView, hourlyData: Hourly?) {
    val nextHoursList = ArrayList<NextHours>()
    hourlyData?.let { hourly ->
        for (i in 1..15) {
            val nextTemp = hourly.temperature2m?.get(i)

            val weatherCodeHourly = hourly.weathercode?.get(i)
            val nextIcon = Utils.weatherImg[weatherCodeHourly]

            val nextHour = hourly.time?.get(i)
            val nextHourTime = LocalDateTime.parse(nextHour).toLocalTime().toString()

            val nextHours = NextHours(nextTemp, nextHourTime, nextIcon)
            nextHoursList.add(nextHours)
        }

        recyclerView.adapter = NextHoursAdapter(nextHoursList)
    }
}


