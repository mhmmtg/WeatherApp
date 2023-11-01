package net.mguler.weatherapp.network

import com.google.gson.annotations.SerializedName

data class Weather(

	@field:SerializedName("elevation")
	val elevation: Int? = null,

	@field:SerializedName("hourly_units")
	val hourlyUnits: HourlyUnits? = null,

	@field:SerializedName("generationtime_ms")
	val generationtimeMs: Double? = null,

	@field:SerializedName("timezone_abbreviation")
	val timezoneAbbreviation: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("utc_offset_seconds")
	val utcOffsetSeconds: Int? = null,

	@field:SerializedName("hourly")
	val hourly: Hourly? = null,

	@field:SerializedName("current_weather")
	val currentWeather: CurrentWeather? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null,

	var cityName: String? = null
)

data class HourlyUnits(

	@field:SerializedName("temperature_2m")
	val temperature2m: String? = null,

	@field:SerializedName("relativehumidity_2m")
	val relativehumidity2m: String? = null,

	@field:SerializedName("weathercode")
	val weathercode: String? = null,

	@field:SerializedName("precipitation_probability")
	val precipitationProbability: String? = null,

	@field:SerializedName("windspeed_10m")
	val windspeed10m: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class Hourly(

	@field:SerializedName("temperature_2m")
	val temperature2m: List<Double?>? = null,

	@field:SerializedName("relativehumidity_2m")
	val relativehumidity2m: List<Int?>? = null,

	@field:SerializedName("weathercode")
	val weathercode: List<Int?>? = null,

	@field:SerializedName("precipitation_probability")
	val precipitationProbability: List<Int?>? = null,

	@field:SerializedName("windspeed_10m")
	val windspeed10m: List<Double?>? = null,

	@field:SerializedName("time")
	val time: List<String?>? = null
)

data class CurrentWeather(

	@field:SerializedName("weathercode")
	val weathercode: Int? = null,

	@field:SerializedName("temperature")
	val temperature: Double? = null,

	@field:SerializedName("windspeed")
	val windspeed: Double? = null,

	@field:SerializedName("is_day")
	val isDay: Int? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("winddirection")
	val winddirection: Int? = null
)
