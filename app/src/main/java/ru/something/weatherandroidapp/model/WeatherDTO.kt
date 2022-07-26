package ru.something.weatherandroidapp.model

data class WeatherDTO(
    val fact: Fact?,
    val forecast: Forecast,
    val info: Info,
    val now: Int,
    val now_dt: String
)

data class Info(
    val lat: Double,
    val lon: Double,
    val url: String
)

data class Fact(
    val condition: String?,
    val daytime: String,
    val feels_like: Int?,
    val humidity: Int,
    val icon: String,
    val obs_time: Int,
    val polar: Boolean,
    val pressure_mm: Int,
    val pressure_pa: Int,
    val season: String,
    val temp: Int?,
    val wind_dir: String,
    //val wind_gust: Double?,
    //val wind_speed: Double
)

data class Forecast(
    val date: String,
    val date_ts: Int,
    val moon_code: Int,
    val moon_text: String,
    val parts: List<Part>,
    val sunrise: String,
    val sunset: String,
    val week: Int
)

data class Part(
    val condition: String,
    val daytime: String,
    val feels_like: Int,
    val humidity: Int,
    val icon: String,
    val part_name: String,
    val polar: Boolean,
    val prec_mm: Double,
    val prec_period: Int,
    val prec_prob: Int,
    val pressure_mm: Int,
    val pressure_pa: Int,
    val temp_avg: Int,
    val temp_max: Int,
    val temp_min: Int,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)