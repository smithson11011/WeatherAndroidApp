package ru.something.weatherandroidapp.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import ru.something.weatherandroidapp.R
import ru.something.weatherandroidapp.databinding.WeatherDetailsFragmentsBinding
import ru.something.weatherandroidapp.model.Weather
import ru.something.weatherandroidapp.model.WeatherDTO
import ru.something.weatherandroidapp.utils.hide
import ru.something.weatherandroidapp.utils.show
import ru.something.weatherandroidapp.viewmodel.WeatherLoader
import java.io.BufferedReader
import java.util.*
import java.util.stream.Collectors

class WeatherDetails : Fragment() {
    // Создание Binding
    private var _binding: WeatherDetailsFragmentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val onLoadListener: WeatherLoader.WeatherLoaderListener =
        object : WeatherLoader.WeatherLoaderListener {
            override fun onLoaded(weatherDTO: WeatherDTO) {
                displayWeather(weatherDTO)
            }

            override fun onFailed(throwable: Throwable) {
            }
        }


    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): WeatherDetails {
            val fragment = WeatherDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeatherDetailsFragmentsBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        binding.mainView.show()
        binding.loadingLayout.hide()
        val loader = WeatherLoader(
            onLoadListener, weatherBundle.city.lat,
            weatherBundle.city.lon
        )
        loader.loadWeather()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
            mainView.show()
            loadingLayout.hide()
            val city = weatherBundle.city
            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            weatherCondition.text = weatherDTO.fact?.condition?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            temperatureValue.text = weatherDTO.fact?.temp.toString()
            feelsLikeValue.text = weatherDTO.fact?.feels_like.toString()
        }
    }


}