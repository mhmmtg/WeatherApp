package net.mguler.weatherapp.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://geocoding-api.open-meteo.com/v1/"
private const val COUNT = 5

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface GeoService {
    @GET("search")
    suspend fun getGeoData(
        @Query("name") name: String,
        @Query("count") count: Int = COUNT
    ): Response<Geo>
}

object GeoAPI {
    val geoService: GeoService by lazy {
        retrofit.create(GeoService::class.java)
    }
}

