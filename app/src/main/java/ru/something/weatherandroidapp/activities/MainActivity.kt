package ru.something.weatherandroidapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.something.weatherandroidapp.R
import ru.something.weatherandroidapp.databinding.ActivityMainBinding
import ru.something.weatherandroidapp.view.NavigationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main, NavigationFragment.newInstance())
                .commit()
        }
    }
}