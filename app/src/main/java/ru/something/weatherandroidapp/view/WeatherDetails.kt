package ru.something.weatherandroidapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.something.weatherandroidapp.databinding.WeatherDetailsFragmentsBinding

class WeatherDetails : Fragment() {
    // Создание Binding
    private var _binding: WeatherDetailsFragmentsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = WeatherDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WeatherDetailsFragmentsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}