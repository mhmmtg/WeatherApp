package net.mguler.weatherapp.network

import com.google.gson.annotations.SerializedName

data class Geo(

	@field:SerializedName("generationtime_ms")
	val generationtimeMs: Any? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class ResultsItem(

	@field:SerializedName("elevation")
	val elevation: Double? = null,

	@field:SerializedName("country_code")
	val countryCode: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("admin1_id")
	val admin1Id: Int? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("admin1")
	val admin1: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("feature_code")
	val featureCode: String? = null,

	@field:SerializedName("country_id")
	val countryId: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)
