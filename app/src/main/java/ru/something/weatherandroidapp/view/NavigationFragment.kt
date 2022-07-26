package ru.something.weatherandroidapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.something.weatherandroidapp.R
import ru.something.weatherandroidapp.activities.AppState
import ru.something.weatherandroidapp.databinding.NavigationFragmentBinding
import ru.something.weatherandroidapp.model.Weather
import ru.something.weatherandroidapp.viewmodel.MainViewModel

class NavigationFragment : Fragment() {

    // Создание Binding
    private var _binding: NavigationFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = NavigationFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NavigationFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                binding.loadingLayout.visibility = View.GONE
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.navigationFragment, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeatherFromLocalSourceRus() }
                    .show()

            }
        }
        Toast.makeText(context, "data", Toast.LENGTH_LONG).show()
    }



}