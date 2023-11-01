package net.mguler.weatherapp.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.open-meteo.com/v1/"
private const val UNITS = "temperature_2m,relativehumidity_2m,precipitation_probability,weathercode,windspeed_10m"
private const val ZONE = "auto"


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface WeatherService {
    @GET("forecast")
    suspend fun getNewWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = UNITS,
        @Query("timezone") timezone: String = ZONE,
        @Query("current_weather") currentWeather: String = "true")
    :Response<Weather>
}

object WeatherAPI {
    val weatherService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}

