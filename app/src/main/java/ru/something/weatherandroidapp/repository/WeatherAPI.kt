package ru.something.weatherandroidapp.repository

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.something.weatherandroidapp.BuildConfig
import ru.something.weatherandroidapp.model.WeatherDTO

interface WeatherAPI {
    @GET("v2/informers")
    fun getWeather(
        @Header("X-Yandex-API-Key") token: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<WeatherDTO>
}

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    )
}

class DetailsRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    DetailsRepository {
    override fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDTO>
    ) {
        remoteDataSource.getWeatherDetails(lat, lon, callback)
    }
}



class RemoteDataSource {
    private val weatherApi = Retrofit.Builder()
        .baseUrl("https://api.weather.yandex.ru/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(WeatherAPI::class.java)
    fun getWeatherDetails(lat: Double, lon: Double, callback:
    Callback<WeatherDTO>
    ) {
        weatherApi.getWeather(
            BuildConfig.WEATHER_API_KEY, lat,
            lon).enqueue(callback)
    }
}