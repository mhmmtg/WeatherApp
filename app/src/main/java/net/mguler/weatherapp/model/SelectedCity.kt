package net.mguler.weatherapp.model

import java.io.Serializable

data class SelectedCity(var cityName: String, var lat: Double, var lng: Double) : Serializable
