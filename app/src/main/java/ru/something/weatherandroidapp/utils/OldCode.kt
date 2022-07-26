package ru.something.weatherandroidapp.utils

import ru.something.weatherandroidapp.R
import ru.something.weatherandroidapp.model.WeatherDTO
import java.util.*

/*private fun displayWeather(weatherDTO: WeatherDTO) {
    with(binding) {
        mainView.show()
        loadingLayout.hide()
        val city = weatherBundle.city
        cityName.text = city.city
        cityCoordinates.text = kotlin.String.format(
            getString(ru.something.weatherandroidapp.R.string.city_coordinates),
            city.lat.toString(),
            city.lon.toString()
        )
        weatherCondition.text = weatherDTO.fact?.condition?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                java.util.Locale.ROOT
            ) else it.toString()
        }
        temperatureValue.text = weatherDTO.fact?.temp.toString()
        feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
    }
} */

/*package ru.something.weatherandroidapp.viewmodel

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import ru.something.weatherandroidapp.BuildConfig
import ru.something.weatherandroidapp.model.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

@RequiresApi(Build.VERSION_CODES.N)
class WeatherLoader(private val listener: WeatherLoaderListener, private val
lat: Double, private val lon: Double) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun loadWeather() {
        try {
            val uri =
                URL("https://api.weather.yandex.ru/informers?lat=${lat}&lon=${lon}")
            val handler = Handler()
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                        "X-Yandex-API-Key",
                        BuildConfig.WEATHER_API_KEY
                    )
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(
                            getLines(bufferedReader),
                            WeatherDTO::class.java
                        )
                    handler.post { listener.onLoaded(weatherDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }

    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
    interface WeatherLoaderListener {
        fun onLoaded(weatherDTO: WeatherDTO)
        fun onFailed(throwable: Throwable)
    }
} */