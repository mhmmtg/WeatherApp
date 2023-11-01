package net.mguler.weatherapp.utils

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.mguler.weatherapp.model.SelectedCity
import net.mguler.weatherapp.network.CurrentWeather
import net.mguler.weatherapp.network.WeatherAPI
import net.mguler.weatherapp.network.Weather

class WeatherViewModel(app: Application) : AndroidViewModel(app) {
    private val _weatherData = MutableLiveData<Weather>()
    val weatherData: LiveData<Weather> get() = _weatherData

    enum class WeatherStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<WeatherStatus>()
    val status: LiveData<WeatherStatus> = _status

    init {
        _weatherData.value = Weather(cityName = "Loading..",
            currentWeather = CurrentWeather(temperature = 0.0, windspeed = 0.0, weathercode = -1)
        )
        val city = getSelectedCity(app)
        getWeatherData(city)
    }

    fun getWeatherData(city: SelectedCity) {
        viewModelScope.launch {
            _status.value = WeatherStatus.LOADING
            try {
                val result = WeatherAPI.weatherService.getNewWeatherData(city.lat, city.lng)
                _weatherData.value = result.body().also {
                    it?.cityName = city.cityName
                }
                _status.value = WeatherStatus.DONE
                //_weatherData.value?.cityName = city.cityName
            } catch (e: Exception) {
                _status.value = WeatherStatus.ERROR
                println(e.localizedMessage)
                //_weatherData.value = listOf()
            }
        }
    }

    private fun getSelectedCity(app: Application): SelectedCity {
        val sp = app.getSharedPreferences(Utils.PREFS, Context.MODE_PRIVATE)

        val spSelectedCity = sp.getString("location", "Globe:0:0")!!
        val listSelectedCity = spSelectedCity.split(":")

        val cityName = listSelectedCity[0]
        val cityLat = listSelectedCity[1].toDouble()
        val cityLng = listSelectedCity[2].toDouble()
        return SelectedCity(cityName, cityLat, cityLng)
    }

}