package ru.something.weatherandroidapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.something.weatherandroidapp.R
import ru.something.weatherandroidapp.databinding.WeatherDetailsFragmentsBinding
import ru.something.weatherandroidapp.model.Weather

class WeatherDetails : Fragment() {
    // Создание Binding
    private var _binding: WeatherDetailsFragmentsBinding? = null
    private val binding get() = _binding!!

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?.let {
            weather -> weather.city.also { city ->
            binding.cityName.text = city.city
            binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            binding.temperatureValue.text = weather.temperature.toString()
            binding.feelsLikeValue.text = weather.feelsLike.toString()
        }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}