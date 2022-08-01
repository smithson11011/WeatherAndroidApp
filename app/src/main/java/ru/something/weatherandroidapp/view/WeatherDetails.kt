package ru.something.weatherandroidapp.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import ru.something.weatherandroidapp.R
import ru.something.weatherandroidapp.activities.AppState
import ru.something.weatherandroidapp.databinding.WeatherDetailsFragmentsBinding
import ru.something.weatherandroidapp.model.Weather
import ru.something.weatherandroidapp.model.WeatherDTO
import ru.something.weatherandroidapp.utils.*
import ru.something.weatherandroidapp.viewmodel.WeatherViewModel
import java.io.IOException

class WeatherDetails : Fragment() {
    // Создание Binding
    private var _binding: WeatherDetailsFragmentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather


    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): WeatherDetails {
            val fragment = WeatherDetails()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this)[WeatherViewModel::class.java]
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
        Glide.with(this)
            .load(getString(R.string.image_header_url)).into(binding.headerImage)
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getWeatherFromRemoteSource(
            weatherBundle.city.lat,
            weatherBundle.city.lon
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.show()
                binding.loadingLayout.hide()
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.hide()
                binding.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.mainView.show()
                binding.loadingLayout.hide()
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getWeatherFromRemoteSource(
                            weatherBundle.city.lat,
                            weatherBundle.city.lon
                        )
                    })

            }
        }
    }

    private fun setWeather(weather: Weather) {
        val city = weatherBundle.city
        binding.cityName.text = city.city
        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            city.lat.toString(),
            city.lon.toString()
        )
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.weatherCondition.text = weather.condition

    }


}